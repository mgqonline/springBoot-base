package com.talkedu.card.sqlprovide;

import com.talkedu.card.common.DatabaseConstants;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BizCardSignSqlprovide {


    public String selectCurrentStudentCourseInfo(final Map<String, Object> param) {
        return new SQL() {
            {
                this.SELECT("pp.class_id");
                this.SELECT("pp.top_org_id");
                this.SELECT("pp.course_id");
                this.SELECT("sc.sdt_id AS std_id");
                this.SELECT("pp.start_date");
                this.SELECT("pp.end_date");
                this.SELECT("pp.class_type");
                this.SELECT("r.`subject`");
                this.SELECT("pp.class_hour");
                this.SELECT("pp.class_teacher as user_id");
                this.SELECT("sc.org_id");
                this.SELECT("pp.teacher_id");
                this.SELECT("pp.start_time");
                this.SELECT("pp.course_detail_id");
                this.SELECT("cc.school_id");
                this.SELECT("sc.pay_id");
                this.SELECT("round( pp.class_hour * f.unit_price, 2 ) use_amount");
                this.SELECT("pp.operator_id");
                this.FROM("(" + new SQL() {
                    {
                        this.SELECT("p.class_id");
                        this.SELECT("p.top_org_id");
                        this.SELECT("p.course_id");
                        this.SELECT("c.class_type");
                        this.SELECT("p.class_hour");
                        this.SELECT("c.class_teacher");
                        this.SELECT("p.teacher_id");
                        this.SELECT("str_to_date( concat( date_format( p.class_date, '%Y-%m-%d' ), ' ', p.start_time, ':00' ), '%Y-%m-%d %H:%i:%s' ) start_date");
                        this.SELECT("str_to_date( concat( date_format( p.class_date, '%Y-%m-%d' ), ' ', p.end_time, ':00' ), '%Y-%m-%d %H:%i:%s' ) end_date");
                        this.SELECT("p.class_date");
                        this.SELECT("p.start_time");
                        this.SELECT("p.course_detail_id");
                        this.SELECT("p.user_id as operator_id");
                        this.FROM(DatabaseConstants.TABLE_BIZ_COURSE_PLAN_DETAIL + " p");
                        this.LEFT_OUTER_JOIN(DatabaseConstants.TABLE_BIZ_CLASS + " c on p.class_id=c.class_id");
                        this.WHERE("p.class_date =  CURRENT_DATE() ");
//                        this.WHERE("c.class_type in ( 1, 2 ) ");
                        this.WHERE("p.`status` = 0 ");
                    }
                } + ") pp");
                this.INNER_JOIN(DatabaseConstants.TABLE_BIZ_SDT_TRAIN_CLASS + " sc on pp.class_id=sc.class_id");
                this.LEFT_OUTER_JOIN(DatabaseConstants.TABLE_GA_FEE_INFO + " f on sc.pay_id = f.pay_id and f.c_status = 0");
                this.LEFT_OUTER_JOIN(DatabaseConstants.TABLE_BIZ_RANGE_COURSE + " r on pp.course_id = r.course_id");
                this.LEFT_OUTER_JOIN(DatabaseConstants.TABLE_BIZ_CLASS + " cc on r.class_id = cc.class_id ");
                this.WHERE(" pp.top_org_id=10");
                this.WHERE(" sc.sdt_id = #{stdId} ");
                this.WHERE("pp.start_date >=#{startDate}");
                this.WHERE("pp.start_date <=#{endDate}");
            }
        }.toString();
    }
}
