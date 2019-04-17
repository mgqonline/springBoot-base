package com.talkedu.card.sqlprovide;

import com.talkedu.card.common.DatabaseConstants;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BizRangeCourseSqlprovide {

    public String updateSignedClassHour(final Map<String,Object> param){
        return new SQL(){
            {
                this.UPDATE(DatabaseConstants.TABLE_BIZ_RANGE_COURSE + " r");
                this.INNER_JOIN("("+ new SQL() {
                    {
                        this.SELECT("course_id");
                        this.SELECT("ifnull(signed_hours,0) signed_hours");
                        this.FROM(DatabaseConstants.TABLE_BIZ_RANGE_COURSE + " t");
                        this.WHERE("t.course_id = #{courseId}");
                    }
                }+") m r.course_id=m.course_id");
                this.SET("r.signed_hours = m.signed_hours + #{signedHours}");
                this.WHERE("r.course_id = #{courseId}");
            }
        }.toString();
    }
}
