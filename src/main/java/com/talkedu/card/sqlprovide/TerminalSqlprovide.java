package com.talkedu.card.sqlprovide;

import com.talkedu.card.common.DatabaseConstants;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class TerminalSqlprovide {

    public String selectDefTerminalInfo(final Map<String, Object> param) {
        return new SQL() {
            {
                this.SELECT("client_id");
                this.SELECT("security_code");
                this.SELECT("school_id");
                this.SELECT("status");
                this.SELECT("card_time");
                this.SELECT("is_net");
                this.SELECT("card_time");
                this.FROM(DatabaseConstants.TABLE_DEFINE_TERMINAL + " t");
                if(param.containsKey("clientId")){
                    this.WHERE("t.client_id = #{clientId}");
                }
                if(param.containsKey("school_id")){
                    this.WHERE("t.school_id =#{schoolId}");
                }
                this.WHERE("status = 0");

            }
        }.toString();
    }

    public String updateNetStatus(final Map<String, Object> param) {
        return new SQL() {
            {
                this.UPDATE(DatabaseConstants.TABLE_DEFINE_TERMINAL);
                if (param.containsKey("status")) {
                    this.SET("status = #{status}");
                }
                if (param.containsKey("isNet")) {
                    this.SET("is_net = #{isNet}");
                }
                if (param.containsKey("remark")) {
                    this.SET("remark = #{remark}");
                }
                if (param.containsKey("cardTime")) {
                    this.SET("card_time = #{cardTime}");
                }
                this.WHERE("client_id = #{clientId}");
            }
        }.toString();
    }
}
