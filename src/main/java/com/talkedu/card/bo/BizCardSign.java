package com.talkedu.card.bo;

import com.talkedu.card.common.DatabaseConstants;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@lombok.Data
@Table(name = DatabaseConstants.TABLE_BIZ_CARD_SIGN)
public class BizCardSign {

    @Id
    private Integer signId;

    private Integer signType;

    private Integer teacherId;

    private Integer studentId;

    private Date pushTime;

    private Integer courseDetailId;

    private Date startTime;
    private Date endTime;

    private Integer classId;
    private Integer schoolId;
    private BigDecimal classHour;
    private Integer subject;


}
