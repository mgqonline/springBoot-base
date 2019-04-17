package com.talkedu.card.mapper;

import com.talkedu.card.bo.BizRangeCourse;
import com.talkedu.card.common.BaseMapper;
import com.talkedu.card.sqlprovide.BizRangeCourseSqlprovide;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Map;

@Mapper
public interface BizRangeCourseMapper extends BaseMapper<BizRangeCourse> {

    @UpdateProvider(type = BizRangeCourseSqlprovide.class,method = "updateSignedClassHour")
    int updateSignedClassHour(Map<String,Object> param);

}
