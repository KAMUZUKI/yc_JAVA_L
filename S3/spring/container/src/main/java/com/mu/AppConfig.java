package com.mu;

import com.mu.bean.Apple;
import org.muframework.annotation.Bean;
import org.muframework.annotation.ComponentScan;
import org.muframework.annotation.Configuration;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 10:13
 **/

@Configuration
@ComponentScan(value={"com.mu.dao","com.mu.bean"})
public class AppConfig {
    @Bean
    public Apple apple(){
        return new Apple();
    }
}
