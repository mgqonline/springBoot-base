package com.talkedu.card.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;


    @Primary
    @Bean(name="systemDataSource")
    @Qualifier("systemDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.system")
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.system.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.system.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.system.password"));
        return dataSource;
    }
}
