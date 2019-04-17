package com.talkedu.card.bo;

import java.math.BigDecimal;
import java.util.Date;

@lombok.Data
public class StudentSignCourseVO implements Cloneable{

    private Integer stdId;
    private Integer courseDetailId;
    private Date startDate;
    private Date endDate;
    private String startTime;
    private Integer payId;
    private Integer topOrgId;
    private Integer courseId;
    private String subject;
    private Integer operatorId;
    private Integer userId;
    private Integer orgId;
    private Integer schoolId;
    private BigDecimal useAmount;
    private BigDecimal classHour;
    private Integer status;
    private Integer teacherId;
    private Integer classId;
    private String sourceType;
    private Integer classType;

    @Override
    public Object clone() throws CloneNotSupportedException {
        StudentSignCourseVO courseVO = null;
        try {
            courseVO =  (StudentSignCourseVO) super.clone();
        }catch (CloneNotSupportedException ex){
            ex.printStackTrace();
        }
        return  courseVO;
    }
}
