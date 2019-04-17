package com.talkedu.card.bo;

import com.talkedu.card.common.DatabaseConstants;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@lombok.Data
@Table(name = DatabaseConstants.TABLE_BIZ_COURSE_PLAN_DETAIL)
public class BizCoursePlanDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = DatabaseConstants.LAST_INSERT_ID)
    private Integer courseDetailId;

    private Integer courseId;
    private String startTime;
    private String endTime;
    private Integer courseWeek;
    private Integer courseHours;
    private Date classDate;
    private BigDecimal classHour;
    private Integer teacherId;
    private Integer originTeacherId;
    private Integer status;
    private Date signDate;
    private Integer isTemp;
    private Integer sort;
    private Integer classId;
    private Integer referenceType;
    private Integer topOrgId;
    private Integer userId;
    private BigDecimal emolumentAmount;
}
