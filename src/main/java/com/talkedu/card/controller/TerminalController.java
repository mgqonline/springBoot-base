package com.talkedu.card.controller;

import com.talkedu.card.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TerminalController {

    @Autowired
    private TerminalService terminalService;


}
