package com.talkedu.card.bo;

import java.io.Serializable;
import java.math.BigDecimal;

@lombok.Data
public class GaFeeDetailVO implements Serializable {

    private Integer payId;
    private Integer classId;
    private Integer studentId;
    private BigDecimal totalAmount;
    private BigDecimal amount;
}
