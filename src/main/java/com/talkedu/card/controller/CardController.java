package com.talkedu.card.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.talkedu.card.bo.*;
import com.talkedu.card.common.util.Constants;
import com.talkedu.card.common.util.TimeUtils;
import com.talkedu.card.service.BizSdtTrainClassService;
import com.talkedu.card.service.CardSignService;
import com.talkedu.card.service.TerminalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
public class CardController {

    @Autowired
    CardSignService cardSignService;

    @Autowired
    TerminalService terminalService;

    @Autowired
    BizSdtTrainClassService sdtTrainClassService;

    @RequestMapping(value = "/clientDataUp")
    @ResponseBody
    public String handleClientDataUp(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Map<String, Object> requestParam = new HashMap<>();
        if (!(requestParam.containsKey("userId") && requestParam.containsKey("signType"))) {
            json.put("code", "-1");
            json.put("message", "打卡参数传值非法");
            return json.toJSONString();
        }
        Integer signType = (Integer) requestParam.get("signType");
        if (signType == Constants.STUDENT_SIGN_TYPE) {
            Map<String, Object> param = new HashMap<>();
            param.put("stdId", requestParam.get("userId"));
            //当前时间半小时前时间计算
            param.put("startDate", TimeUtils.getNowRangeTime(-30 * 60));
            //当前时间半小时后时间计算
            param.put("endDate", TimeUtils.getNowRangeTime(30 * 60));
            List<StudentSignCourseVO> signList = cardSignService.selectCurrentStudentCourseInfo(param);
            //一对一和一对多待签到课程信息
            List<StudentSignCourseVO> oneRecord = null;
            //班课待签到记录
            StudentSignCourseVO smallClassRecord = null;
            if (signList != null && signList.size() > 0) {
                for (StudentSignCourseVO studentCourseRecord : signList) {
                    oneRecord = new ArrayList<>();
                    //一对一
                    if (studentCourseRecord.getClassType() == 1) {
                        oneRecord.add(studentCourseRecord);
                        break;
                    }
                    //一对多的业务判断需要找到与当前学生一起上课的那个学生信息
                    if (studentCourseRecord.getClassType() == 2) {
                        Map<String, Object> stdParam = new HashMap<>();
                        stdParam.put("classId", studentCourseRecord.getClassId());
                        List<BizSdtTrainClass> list = sdtTrainClassService.selectSdtTrainClass(stdParam);

                        //处理一对多多个学生的待签到课程信息,一起签完
                        if (null != list && list.size() > 0) {
                            for (BizSdtTrainClass sdtTrainClass : list) {
                                try {
                                    StudentSignCourseVO courseVO = (StudentSignCourseVO) studentCourseRecord.clone();
                                    courseVO.setStdId(sdtTrainClass.getSdtId());
                                    oneRecord.add(courseVO);
                                } catch (CloneNotSupportedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
                if(null == oneRecord) {
                    for (StudentSignCourseVO studentCourseRecord : signList) {
                        if (studentCourseRecord.getClassType() == 0 || studentCourseRecord.getClassType() == 3) {
                            smallClassRecord = studentCourseRecord;
                            break;
                        }
                    }
                }
            }
            //一对一(包括一对多)打卡签到处理
            if (null != oneRecord && oneRecord.size() > 0) {
                cardSignService.handleStudentSign(oneRecord);
//                StudentSignCourseVO
            }

            //学生没有一对一上课且有班课打卡签到处理
            if (oneRecord.size() == 0 && smallClassRecord != null) {
                cardSignService.saveCardSign(smallClassRecord);
                CourseVO result = new CourseVO();

            }
        }

        //老师签到逻辑,待续...FIXME
        if (signType == Constants.TEACHER_SIGN_TYPE) {

        }
        BizCardSign cardSign = new BizCardSign();
        json = JSONObject.parseObject(JSON.toJSONString(requestParam));
        log.debug(">>>>>>>>>>>>>>>>>>>>>>>" + json);
        return json.toJSONString();
    }

    @RequestMapping("/handleSysCurrentTime")
    @ResponseBody
    public String getCurrentSystemTime() {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder builder = new StringBuilder();
        return builder.append("1$").append(sdf.format(currentTime)).toString();
    }

    @RequestMapping("/clientUpDevice")
    @ResponseBody
    public String ClientUpDevice(HttpServletRequest request) {
        /*&clientId=9601112&areaCode=ARBJ02&json={"System":"V3.15","Ip
                ":"192.168.1.104"}&sign=87F1523BE7F66D7DC773EA13CB42611B*/
        DefTerminal terminal = new DefTerminal();
        String areaCode = (String) request.getParameter("areaCode");
        if (areaCode == null) {
            areaCode = "13";
        }
        terminal.setSchoolId(Integer.valueOf(areaCode));
        terminal.setIsNet(0);
        terminal.setStatus(Constants.DEF_TERMINAL_STATUS);
        String json = request.getParameter("json");
        terminal.setRemark("第一次上传设备数据存储,以后每10分钟发一次数据校验");
        if (json != null) {
            JSONObject object = JSONObject.parseObject(json);
            if (object.containsKey("System")) {
                terminal.setOsName(object.getString("System"));
                terminal.setIpAddress(object.getString("Ip"));
            }
        }
        String clientId = request.getParameter("clientId");
        terminal.setClientId(clientId);
        String sign = request.getParameter("sign");
        terminal.setSecurityCode(sign);
        terminal.setCreateTime(TimeUtils.getTime(System.currentTimeMillis()));
        try {
            terminalService.handleTernimalInfo(terminal);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "RECEIVE FAIL";
        }
        return "SUCCESS";
    }

    @RequestMapping("/conntectDownTime")
    @ResponseBody
    public String conntectDownTime(HttpServletRequest request) {
        //sign=snkayK581^#W3EG6
        String cliendId = request.getParameter("clientId");
        String sign = request.getParameter("sign");
        log.debug("心跳检查:>>>>>>>>>>>>>>>>>>>>" + cliendId);
        log.debug(">>>>>>>>>>>>>>>>>>>>" + sign);
        DefTerminal def = new DefTerminal();
        def.setClientId(cliendId);
        def.setIsNet(Constants.DEF_TERMINAL_IS_NET_NORMAL);
        def.setCardTime(new Date());
        try {
            terminalService.updateTernimalNetStatus(def);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "SUCCESS";
    }
}
