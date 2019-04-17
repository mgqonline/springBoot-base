package com.talkedu.card.sqlprovide;

import com.talkedu.card.common.DatabaseConstants;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BizSdtTrainClassSqlprovide {

    public String selectSdtTrainClass(final Map<String, Object> param) {
        return new SQL() {
            {
                this.SELECT("sdt_id");
                this.SELECT("class_id");
                this.FROM(DatabaseConstants.TABLE_BIZ_SDT_TRAIN_CLASS);
                this.WHERE("class_id = #{classId}");
            }
        }.toString();
    }
}
