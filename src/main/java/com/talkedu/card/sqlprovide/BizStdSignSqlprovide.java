package com.talkedu.card.sqlprovide;

import com.talkedu.card.common.DatabaseConstants;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BizStdSignSqlprovide {

    public String selectSignedStudentClassHourAndAmount(final Map<String, Object> param) {
        return new SQL() {
            {
                this.SELECT("std_id as student_id");
                this.SELECT("class_id");
                this.SELECT("sum(class_hour) as class_hour");
                this.SELECT("sum(use_amount) as use_amount");
                this.FROM(DatabaseConstants.TABLE_BIZ_STD_SIGN);
                this.WHERE("top_org_id = #{topOrgId}");
                this.WHERE("std_id = #{stdId}");
                this.WHERE("class_id = #{classId}");
                this.GROUP_BY("class_id,std_id");
            }
        }.toString();
    }

    public String selectStudentRealAmount(final Map<String,Object> param){
        return new SQL(){
            {
                this.SELECT("f2.pay_id");
                this.SELECT("f2.class_id");
                this.SELECT("f2.sdt_id as student_id");
                this.SELECT("f2.totalamount");
                this.SELECT("sum(case when f1.oper_type = 5 and f1.amount > 0 then -f1.amount when f1.oper_type = 4 then -f1.amount else f1.amount end) amount");
                this.FROM(DatabaseConstants.TABLE_GA_FEE_DETAIL + " f1");
                this.LEFT_OUTER_JOIN(DatabaseConstants.TABLE_GA_FEE_INFO + " f2 on f1.pay_id=f2.pay_id");
                this.WHERE("f2.sdt_id= #{stdId}");
                this.WHERE("f2.class_id=#{classId}");
                this.WHERE("c_status = 0 ");
                this.GROUP_BY("f2.pay_id,f2.class_id,f2.sdt_id,f2.totalamount");
            }
        }.toString();
    }

    public String selectStudentComprehensiveAmount(final Map<String,Object> param){
        return new SQL(){
            {
                this.SELECT("t1.pay_id");
                this.SELECT("t2.sdt_id as student_id");
                this.SELECT("t2.class_id");
                this.SELECT("t1.pay_type");
                this.SELECT("ifnull(t1.total_fee,0) total_fee");
                this.SELECT("ifnull(t1.amount,0) amount");
                this.SELECT("ifnull(t1.course_hour,0) course_hour");
                this.SELECT("ifnull(t1.free_hour,0) free_hour");
                this.SELECT("t1.subject");
                this.SELECT("t1.revision_hour");
                this.SELECT("ifnull(t1.unit_price,0) unit_price");
                this.SELECT("t1.payment_date");
                this.FROM(DatabaseConstants.TABLE_GA_FEE_ITEM + " t1");
                this.LEFT_OUTER_JOIN(DatabaseConstants.TABLE_GA_FEE_INFO + " t2 on t1.pay_id=t2.pay_id");
                this.WHERE("t2.sdt_id=#{stdId");
                this.WHERE("t2.class_id=#{classId}");
                this.WHERE("t2.c_status= 0 ");
            }
        }.toString();
    }
}
