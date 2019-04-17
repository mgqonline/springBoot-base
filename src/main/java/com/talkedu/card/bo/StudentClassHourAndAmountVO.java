package com.talkedu.card.bo;

import java.math.BigDecimal;

@lombok.Data
public class StudentClassHourAndAmountVO {

    private Integer studentId;
    private Integer classId;
    private BigDecimal classHour;
    private BigDecimal useAmount;
}
