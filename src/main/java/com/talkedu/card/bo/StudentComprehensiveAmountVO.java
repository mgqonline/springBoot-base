package com.talkedu.card.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@lombok.Data
public class StudentComprehensiveAmountVO implements Serializable {

    private Integer studentId;
    private Integer classId;
    private Integer payId;
    private BigDecimal amount;
    //缴费课时
    private BigDecimal courseHour;
    //赠送课时
    private BigDecimal freeHour;
    //缴费金额
    private BigDecimal totalFee;
    //支付类型
    private Integer payType;
    //科目
    private Integer subject;
    //操作时间
    private Date paymentDate;

    //修正课时
    private BigDecimal revisionHour;

    //单价
    private BigDecimal unitPrice;
}
