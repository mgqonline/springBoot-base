package com.talkedu.card.mapper;

import com.talkedu.card.bo.BizSdtTrainClass;
import com.talkedu.card.common.BaseMapper;
import com.talkedu.card.sqlprovide.BizSdtTrainClassSqlprovide;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface BizSdtTrainClassMapper extends BaseMapper<BizSdtTrainClass> {

    @ResultType(BizSdtTrainClass.class)
    @SelectProvider(type = BizSdtTrainClassSqlprovide.class,method = "selectSdtTrainClass")
    List<BizSdtTrainClass> selectSdtTrainClass(Map<String,Object> param);
}
