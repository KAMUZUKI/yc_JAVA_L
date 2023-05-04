package com.mu.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : MUZUKI
 * @date : 2023-04-13 20:43
 **/

@Configuration
public class FeignLogConfig {
    @Bean
        //NONE,BASIC HEADERS,FULL,
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
