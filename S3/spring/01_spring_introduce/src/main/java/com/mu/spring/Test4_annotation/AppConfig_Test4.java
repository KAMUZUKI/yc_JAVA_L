package com.mu.spring.Test4_annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 18:01
 **/

@Configuration
@ComponentScan("com.mu.spring.Test4_annotation")
public class AppConfig_Test4 {
    // @Bean原来是利用  @Bean创建Bean，交给spring托管
}
