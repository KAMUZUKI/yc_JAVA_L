package com.mu.logaopproject;

import com.mu.logaopproject.config.LogAspect;
import com.mu.logaopproject.config.LogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "mu.log", value = "enabled")
@EnableConfigurationProperties(LogProperties.class)
public class AopAutoConfiguration {
    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}