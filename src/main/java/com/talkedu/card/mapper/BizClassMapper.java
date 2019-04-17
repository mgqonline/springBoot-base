package com.talkedu.card.mapper;

import com.talkedu.card.bo.BizClass;
import com.talkedu.card.common.BaseMapper;
import com.talkedu.card.sqlprovide.BizClassSqlprovide;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface BizClassMapper extends BaseMapper<BizClass> {

    @ResultType(BizClass.class)
    @SelectProvider(type = BizClassSqlprovide.class,method = "selectClassList")
    List<BizClass> selectClassList(Map<String,Object> params);
}
