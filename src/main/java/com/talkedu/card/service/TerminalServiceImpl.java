package com.talkedu.card.service;

import com.talkedu.card.bo.DefTerminal;
import com.talkedu.card.common.util.ObjectUtils;
import com.talkedu.card.mapper.TerminalMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TerminalServiceImpl extends BaseServiceImpl<DefTerminal> implements TerminalService {

    @Autowired
    TerminalMapper defMapper;

    @Override
    public int updateTernimalNetStatus(DefTerminal def) throws Exception {
        Map<String, Object> param = ObjectUtils.objectToMap(def);
        return defMapper.updateNetStatus(param);
    }

    @Override
    public int handleTernimalInfo(DefTerminal def) {
        if(StringUtils.isNotBlank(def.getClientId())) {
            Map<String, Object> param = new HashMap<>();
            param.put("clientId", def.getClientId());
            List<DefTerminal> terminal = defMapper.selectDefTerminalInfo(param);
            if(terminal == null && terminal.size() == 0){
                defMapper.insertSelective(def);
            }else{
                defMapper.updateByPrimaryKeySelective(def);
            }
        }
        return 1;
    }
}
