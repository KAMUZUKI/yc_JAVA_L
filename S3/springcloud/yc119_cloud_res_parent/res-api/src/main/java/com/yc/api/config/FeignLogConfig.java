package com.yc.api.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: yc119_cloud_res_parent
 * @description:
 * @author: zy
 * @create: 2023-04-13 20:12
 */
@Configuration
public class FeignLogConfig {
    @Bean        //NONE,BASIC HEADERS,FULL,
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
