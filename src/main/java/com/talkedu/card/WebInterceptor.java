package com.talkedu.card;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebInterceptor extends WebMvcConfigurationSupport {


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
//        registry.addInterceptor(new CardInterceptiorHandle()).addPathPatterns("/card/**");
    }
}
