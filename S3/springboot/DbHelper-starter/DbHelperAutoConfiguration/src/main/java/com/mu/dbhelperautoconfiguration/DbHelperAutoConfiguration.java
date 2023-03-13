package com.mu.dbhelperautoconfiguration;

import com.mu.dbhelperautoconfiguration.dbhelper.DbHelper;
import com.mu.dbhelperautoconfiguration.dbhelper.DbHelperProperties;
import com.mysql.cj.jdbc.Driver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MUZUKI
 */

@Configuration
@ConditionalOnBean(Driver.class)
@EnableConfigurationProperties(DbHelperProperties.class)   //将DbHelperProperties托管
public class DbHelperAutoConfigurationApplication {
    @Bean
    public DbHelper dbHelper(){
        return new DbHelper();
    }
}
