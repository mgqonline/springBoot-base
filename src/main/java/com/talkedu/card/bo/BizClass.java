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
@Table(name = DatabaseConstants.TABLE_BIZ_CLASS)
public class BizClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = DatabaseConstants.LAST_INSERT_ID)
    private Integer classId;
    private String classCode;
    private Integer topOrgId;
    private Integer schoolId;
    private String className;
    private String classGrade;
    private Integer classTeacher;
    private Date startDate;
    private Date endDate;
    private Integer mostCnts;
    private Integer fullCnts;
    private Integer classCnts;
    private Integer roomId;
    private Integer cpId;
    private Integer classHourCnts;
    private String remark;
    private Date updateDate;
    private Integer userId;
    private Integer orgId;
    private Integer status;
    private Integer classType;
    private Integer classStatus;
    private Integer students;
    private Date createDate;
    private Integer tuifeiCount;
    private Integer transinCount;
    private Integer transoutCount;
    private Integer classHourLong;
    private Integer cpYear;
    private String cpQuarter;
    private String classProp;
    private BigDecimal totalClassHour;
    private Integer parentclass;
    private Integer xnstatus;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String classProperty;
    private Integer isIssue;
    private Integer isOnline;
    private Integer redirectUrl;
    private String courseIntroduction;
    private String applicableStd;
    private Integer itemId;
    private Integer courseNumber;
    private Integer referenceClass;
    private Date effectDate;
    private Date expireDate;
    private String courseType;
    private Integer classAttr;
}
