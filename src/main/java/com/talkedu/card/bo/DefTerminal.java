package com.talkedu.card.bo;

import com.talkedu.card.common.DatabaseConstants;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@lombok.Data
@Table(name = DatabaseConstants.TABLE_DEFINE_TERMINAL)
public class DefTerminal {

    @Id
    private Integer terminelId;

    @NotNull
    private String clientId;

    @NotNull
    private String securityCode;

    private Integer status;

    @NotNull
    private Integer schoolId;

    private Integer isNet;

    private Date cardTime;

    private String remark;

    private Date createTime;

    private Integer userId;

    private Integer orgId;

    private String osName;

    private String ipAddress;


}
