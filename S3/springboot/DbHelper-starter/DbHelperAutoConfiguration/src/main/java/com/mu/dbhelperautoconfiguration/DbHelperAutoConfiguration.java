package com.mu.dbhelperautoconfiguration;

import com.mu.dbhelperautoconfiguration.dbhelper.DbHelper;
import com.mu.dbhelperautoconfiguration.dbhelper.DbHelperProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MUZUKI
 */

@Configuration
@ConditionalOnClass(DbHelper.class)
@EnableConfigurationProperties(DbHelperProperties.class)   //将DbHelperProperties托管
public class DbHelperAutoConfiguration {
    @Bean
    public DbHelper dbHelper(){
        DbHelper dbHelper = new DbHelper();
        return dbHelper;
    }
}
