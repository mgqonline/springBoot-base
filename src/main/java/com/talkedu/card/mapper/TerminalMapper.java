package com.talkedu.card.mapper;

import com.talkedu.card.bo.DefTerminal;
import com.talkedu.card.common.BaseMapper;
import com.talkedu.card.sqlprovide.TerminalSqlprovide;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;
import java.util.Map;


@Mapper
public interface TerminalMapper extends BaseMapper<DefTerminal> {

    @UpdateProvider(type = TerminalSqlprovide.class, method = "updateNetStatus")
    int updateNetStatus(Map<String,Object> object);

    @ResultType(DefTerminal.class)
    @SelectProvider(type = TerminalSqlprovide.class,method = "selectDefTerminalInfo")
    List<DefTerminal> selectDefTerminalInfo(Map<String,Object> param);
}
