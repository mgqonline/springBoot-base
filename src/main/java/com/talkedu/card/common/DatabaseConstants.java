package com.talkedu.card.common;

public class DatabaseConstants {

    public static final String LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";

    public static final String SCHEMA_TALKWEB = "`talkweb`.";

    //打卡机终端信息表
    public static final String TABLE_DEFINE_TERMINAL = SCHEMA_TALKWEB + "`t_def_terminal` ";

    //学生打卡签到表
    public static final String TABLE_BIZ_CARD_SIGN = SCHEMA_TALKWEB + "`t_biz_card_sign` ";

    //班级表
    public static final String TABLE_BIZ_CLASS = SCHEMA_TALKWEB + "`t_biz_class` ";

    //排课计划表
    public static final String TABLE_BIZ_RANGE_COURSE = SCHEMA_TALKWEB + "`t_biz_range_course` ";

    //排课计划明细表
    public static final String TABLE_BIZ_COURSE_PLAN_DETAIL = SCHEMA_TALKWEB + "`t_biz_course_plan_detail` ";

    //学生签到表
    public static final String TABLE_BIZ_STD_SIGN = SCHEMA_TALKWEB + "`t_biz_std_sign` ";

    //学生报班表
    public static final String TABLE_BIZ_SDT_TRAIN_CLASS = SCHEMA_TALKWEB + "`t_biz_sdt_train_class` ";

    //缴费表
    public static final String TABLE_GA_FEE_INFO = SCHEMA_TALKWEB + "`t_ga_fee_info` ";

    //缴费明细表
    public static final String TABLE_GA_FEE_DETAIL = SCHEMA_TALKWEB + "`t_ga_fee_detail` ";

    //缴费项目表
    public static final String TABLE_GA_FEE_ITEM = SCHEMA_TALKWEB + "`t_ga_fee_item` ";

}
