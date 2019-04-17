package com.talkedu.card.sqlprovide;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BizClassSqlprovide {

    public String selectClassList(final Map<String,Object> param){
        return new SQL(){
            {

            }
        }.toString();
    }
}
