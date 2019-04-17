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
@Table(name = DatabaseConstants.TABLE_BIZ_RANGE_COURSE)
public class BizRangeCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = DatabaseConstants.LAST_INSERT_ID)
    private Integer courseId;
    private Integer classId;
    private Integer courseStatus;
    private Integer teacherId;
    private Integer subject;
    private Date classDate;
    private Integer teachPeriod;
    private BigDecimal paid;
    private String remakr;
    private Date setCouresDate;
    private BigDecimal classpaid;
    private BigDecimal courseMoney;
    private Integer userId;
    private Date createTime;
    private Integer orgId;
    private Date updateTime;
    private Integer updateOrgId;
    private Integer updateUserId;
    private BigDecimal planClassHours;
    private Date classTime;
    private Integer autoSign;
    private BigDecimal signedHours;
}
