package com.talkedu.card.service;

import com.talkedu.card.bo.*;
import com.talkedu.card.common.util.Constants;
import com.talkedu.card.common.util.TimeUtils;
import com.talkedu.card.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CardSignServiceImpl extends BaseServiceImpl<BizCardSign> implements CardSignService {

    @Autowired
    private BizClassMapper classMapper;

    @Autowired
    private BizCoursePlanDetailMapper coursePlanDetailMapper;

    @Autowired
    private BizRangeCourseMapper rangeCourseMapper;

    @Autowired
    private BizCardSignMapper cardSignMapper;

    @Autowired
    private BizStdSignMapper stdSignMapper;

    @Transactional
    public void saveCardSign(StudentSignCourseVO record) {
        BizCardSign sign = new BizCardSign();
        sign.setCourseDetailId(record.getCourseDetailId());
        sign.setPushTime(TimeUtils.getTime(System.currentTimeMillis()));
        sign.setStudentId(record.getStdId());
        sign.setClassId(record.getClassId());
        sign.setSchoolId(record.getSchoolId());
        sign.setClassHour(record.getClassHour());
        sign.setStartTime(record.getStartDate());
        sign.setEndTime(record.getEndDate());
        sign.setSignType(Constants.STUDENT_SIGN_TYPE);
        sign.setSubject(Integer.valueOf(record.getSubject()));
        cardSignMapper.insertSelective(sign);
    }

    @Override
    public List<StudentSignCourseVO> selectCurrentStudentCourseInfo(Map<String, Object> param) {
        return cardSignMapper.selectCurrentStudentCourseInfo(param);
    }

    @Override
    @Transactional
    public void handleStudentSign(List<StudentSignCourseVO> courseList) {
        if (null == courseList) {
            return;
        }
        StudentSignCourseVO courseObj = courseList.get(0);
        Integer classId = courseObj.getClassId();
        BizClass classInfo = classMapper.selectByPrimaryKey(classId);
        for (StudentSignCourseVO courseVO : courseList) {
            BigDecimal willSignClassHour = courseVO.getClassHour();
            Map<String, Object> signQuery = new HashMap<>();
            signQuery.put("classId", courseVO.getClassId());
            signQuery.put("stdId", courseVO.getStdId());
            //查询已签课时和消耗金额结果
            List<StudentClassHourAndAmountVO> signedResultList = stdSignMapper.selectSignedStudentClassHourAndAmount(signQuery);
            StudentClassHourAndAmountVO signedResult = null;
            if (signedResultList != null && signedResultList.size() > 0) {
                signedResult = signedResultList.get(0);
            }
            //已签课时数
            BigDecimal signClassHour = (signedResult == null ? BigDecimal.ZERO : signedResult.getClassHour());

            //已签到课耗金额
            BigDecimal signUseAmount = BigDecimal.ZERO;
            if (signedResult != null) {
                //已签课时数+待签课时数
                BigDecimal factSignCounts = signClassHour.add(willSignClassHour);
                if (factSignCounts.compareTo(classInfo.getTotalClassHour()) > 0) {
                    throw new RuntimeException("签到课时数不能大于开班课时数");
                }
                signUseAmount = signedResult.getUseAmount();
            }
            List<StudentComprehensiveAmountVO> comprehensiveList = stdSignMapper.selectStudentComprehensiveAmount(signQuery);
            if (null == comprehensiveList) {
                log.error("签到一对一班级学生的缴费项目为空异常:classId=" + classId + ",stdId=" + courseVO.getStdId());
                throw new RuntimeException("签到一对一班级学生的缴费项目查询为空异常,请联系管理员");
            }
            //一对一课耗单价从item表根据对应科目获取 by 2018-09-26 modify
            BigDecimal signUnitPrice = BigDecimal.ZERO;
            BigDecimal comprehensive = BigDecimal.ZERO;
            BigDecimal totalClassHours = BigDecimal.ZERO;
            for (StudentComprehensiveAmountVO item : comprehensiveList) {
                if (item.getPayType() == 1
                        && Integer.valueOf(courseVO.getSubject()) == item.getSubject()) {
                    signUnitPrice = item.getUnitPrice();
                } else {
                    comprehensive = comprehensive.add(item.getTotalFee());
                }
                if (item.getCourseHour().compareTo(BigDecimal.ZERO) > 0) {
                    totalClassHours = totalClassHours.add(item.getCourseHour());
                    if (item.getFreeHour().compareTo(BigDecimal.ZERO) > 0) {
                        totalClassHours = totalClassHours.add(item.getFreeHour());
                    }
                }
            }
            if (signUnitPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("签到单价数据异常，请联系管理员");
            }
            List<GaFeeDetailVO> detailResult = stdSignMapper.selectStudentRealAmount(signQuery);
            if (null == detailResult || detailResult.size() == 0) {
                throw new RuntimeException("校验签到查询缴费明细为空异常");
            }
            GaFeeDetailVO detail = detailResult.get(0);
            //实际缴费金额
            BigDecimal amount = detail.getAmount();
            BigDecimal classAmount = BigDecimal.ZERO;

            //课时费是否小于等于0
            boolean isAmount = amount.compareTo(BigDecimal.ZERO) > 0;
            if (amount.compareTo(BigDecimal.ZERO) > 0) {
                //实际缴费课时费
                classAmount = amount.subtract(comprehensive);
            }

            BigDecimal balanceClassHour = BigDecimal.ZERO;
            //是否最后签到课次
            boolean isLast = false;

            //计算实际缴费课时(为了兼容历史原因欠费,需要实时统计计算,新业务不允许欠费)
            BigDecimal factFeeClassHour = classAmount.divide(signUnitPrice, 1, BigDecimal.ROUND_HALF_UP);
            balanceClassHour = factFeeClassHour.subtract(signClassHour);//当前实际剩余课时数
            if (balanceClassHour.compareTo(courseVO.getClassHour()) < 0) {
                throw new RuntimeException("一对一班级的实际签到课时数应为【" + balanceClassHour + "】");
            } else if (balanceClassHour.compareTo(courseVO.getClassHour()) == 0) {
                isLast = true;
            }

            BizStdSign stdSign = new BizStdSign();
            stdSign.setClassHour(courseVO.getClassHour());
            stdSign.setClassId(courseVO.getClassId());
            stdSign.setCourseDetailId(courseVO.getCourseDetailId());
            stdSign.setCourseId(courseVO.getCourseId());
            stdSign.setOperatorId(courseVO.getStdId());
            stdSign.setSchoolId(courseVO.getSchoolId());
            stdSign.setUserId(courseVO.getUserId());
            stdSign.setOrgId(courseVO.getOrgId());
            stdSign.setSourceType("PUNCH-CARD");
            stdSign.setStartDate(courseVO.getStartDate());
            stdSign.setEndDate(courseVO.getEndDate());
            stdSign.setPayId(courseVO.getPayId());
            //此次消耗课时金额,判断是否最后一次签到计算消耗
            BigDecimal useAmount = isLast ? classAmount.subtract(signUseAmount) : signUnitPrice.multiply(courseVO.getClassHour()).setScale(2, BigDecimal.ROUND_HALF_UP);
            stdSign.setUseAmount(useAmount);
            stdSign.setStartTime(courseVO.getStartTime());
            stdSign.setOperDate(TimeUtils.getTime(System.currentTimeMillis()));
            stdSignMapper.insertSelective(stdSign);
        }

        //更新某次课的签到状态和签到时间信息
        updateCoursePlanDetailInfo(courseObj);

        //更新排课计划表已签课时数
        updateSignedCourseHour(courseObj);

        //处理该班级是否最后一次签到课并更新班级状态
        handleClassStatus(classInfo);

        //记录签到信息
        saveCardSign(courseObj);
    }


    //处理班级状态信息,班级已签课时数跟开班课时数对比,相等就表示可以结课
    private void handleClassStatus(BizClass classInfo) {
        BizClass clazz = new BizClass();
        clazz.setClassId(classInfo.getClassId());
        clazz.setClassStatus(Constants.TALKEDU_BIZ_CLASS_STATUS_CLASSING);

        BizRangeCourse param = new BizRangeCourse();
        param.setClassId(classInfo.getClassId());
        List<BizRangeCourse> signedList = rangeCourseMapper.select(param);
        BigDecimal sumSignedHours = BigDecimal.ZERO;
        if (signedList != null) {
            for (BizRangeCourse course : signedList) {
                BigDecimal tempVal = course.getSignedHours() == null ? BigDecimal.ZERO : course.getSignedHours();
                sumSignedHours = sumSignedHours.add(tempVal);
            }
        }
        //是最后一次课
        if (classInfo != null && classInfo.getTotalClassHour() != null &&
                classInfo.getTotalClassHour().compareTo(sumSignedHours) == 0) {
            clazz.setClassStatus(Constants.TALKEDU_BIZ_CLASS_STATUS_OVER);
            //对班级结束时间进行更新
            clazz.setEndDate(TimeUtils.getTime(System.currentTimeMillis()));
        }

        classMapper.updateByPrimaryKeySelective(clazz);
    }

    private void updateSignedCourseHour(StudentSignCourseVO courseObj) {
        Map<String, Object> rangeCourse = new HashMap<>();
        rangeCourse.put("courseId", courseObj.getCourseId());
        rangeCourse.put("signedHours", courseObj.getClassHour());
        //更新已签课时数
        rangeCourseMapper.updateSignedClassHour(rangeCourse);
    }

    private void updateCoursePlanDetailInfo(StudentSignCourseVO courseObj) {
        BizCoursePlanDetail planDetail = new BizCoursePlanDetail();
        planDetail.setStatus(Constants.TALKEDU_COURSE_PLAN_DETAIL_STATUS_SIGNIN);
        planDetail.setCourseDetailId(courseObj.getCourseDetailId());
        planDetail.setTeacherId(courseObj.getTeacherId());
        planDetail.setClassHour(courseObj.getClassHour());
        coursePlanDetailMapper.updateByPrimaryKeySelective(planDetail);
    }
}
