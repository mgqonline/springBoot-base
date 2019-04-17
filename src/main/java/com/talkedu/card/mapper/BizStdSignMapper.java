package com.talkedu.card.mapper;

import com.talkedu.card.bo.BizStdSign;
import com.talkedu.card.bo.GaFeeDetailVO;
import com.talkedu.card.bo.StudentClassHourAndAmountVO;
import com.talkedu.card.bo.StudentComprehensiveAmountVO;
import com.talkedu.card.common.BaseMapper;
import com.talkedu.card.sqlprovide.BizStdSignSqlprovide;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface BizStdSignMapper extends BaseMapper<BizStdSign> {


    @ResultType(StudentClassHourAndAmountVO.class)
    @SelectProvider(type = BizStdSignSqlprovide.class,method = "selectSignedStudentClassHourAndAmount")
    List<StudentClassHourAndAmountVO> selectSignedStudentClassHourAndAmount(Map<String,Object> param);


    @ResultType(GaFeeDetailVO.class)
    @SelectProvider(type = BizStdSignSqlprovide.class,method = "selectStudentRealAmount")
    List<GaFeeDetailVO> selectStudentRealAmount(Map<String,Object> param);


    @ResultType(StudentComprehensiveAmountVO.class)
    @SelectProvider(type = BizStdSignSqlprovide.class,method = "selectStudentComprehensiveAmount")
    List<StudentComprehensiveAmountVO> selectStudentComprehensiveAmount(Map<String,Object> param);


}
