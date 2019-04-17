package com.talkedu.card.service;

import com.talkedu.card.bo.DefTerminal;

public interface TerminalService extends BaseService<DefTerminal> {

    int updateTernimalNetStatus(DefTerminal def)  throws Exception;

    int handleTernimalInfo(DefTerminal def);

}
