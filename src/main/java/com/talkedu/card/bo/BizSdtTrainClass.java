package com.talkedu.card.bo;

import com.talkedu.card.common.DatabaseConstants;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@lombok.Data
@Table(name = DatabaseConstants.TABLE_BIZ_SDT_TRAIN_CLASS)
public class BizSdtTrainClass {

    private Integer sdtId;
    private Integer classId;
    private Date signDate;
    private BigDecimal courseAmount;
    private Integer payId;

    private Integer userId;
    private Integer orgId;
}
