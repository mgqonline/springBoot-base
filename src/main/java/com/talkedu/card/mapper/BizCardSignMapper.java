package com.talkedu.card.mapper;

import com.talkedu.card.bo.BizCardSign;
import com.talkedu.card.bo.StudentSignCourseVO;
import com.talkedu.card.common.BaseMapper;
import com.talkedu.card.sqlprovide.BizCardSignSqlprovide;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface BizCardSignMapper extends BaseMapper<BizCardSign> {


    @ResultType(StudentSignCourseVO.class)
    @SelectProvider(type = BizCardSignSqlprovide.class,method = "selectCurrentStudentCourseInfo")
    List<StudentSignCourseVO> selectCurrentStudentCourseInfo(Map<String,Object> param);
}
