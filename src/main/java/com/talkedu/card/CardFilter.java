package com.talkedu.card;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class CardFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Enumeration keys = servletRequest.getParameterNames();
        Map<String, Object> requestParam = new HashMap<>();
        while (keys.hasMoreElements()) {
            String paraName = (String) keys.nextElement();
            Object objVal = servletRequest.getParameter(paraName);
            requestParam.put(paraName, objVal);
            System.out.println(paraName + ": " + servletRequest.getParameter(paraName));
        }
    }
}
