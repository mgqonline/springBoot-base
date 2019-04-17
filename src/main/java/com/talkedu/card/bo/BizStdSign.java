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
@Table(name=DatabaseConstants.TABLE_BIZ_STD_SIGN)
public class BizStdSign implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = DatabaseConstants.LAST_INSERT_ID)
    private Integer signId;
    private Integer classId;
    private Integer topOrgId;
    private Integer courseId;
    private Integer stdId;
    private String sourceType;
    private Date startDate;
    private Date endDate;
    private Integer subject;
    private BigDecimal classHour;
    private Date operDate;
    private Integer userId;
    private Integer orgId;
    private Integer teacherId;
    private String startTime;
    private Integer courseDetailId;
    private Integer schoolId;
    private Integer payId;
    private BigDecimal useAmount;
    private Integer operatorId;
}
