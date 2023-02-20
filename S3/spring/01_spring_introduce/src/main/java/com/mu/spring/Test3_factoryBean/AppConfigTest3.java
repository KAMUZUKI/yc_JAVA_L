package com.mu.spring.Test3_factoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 17:21
 **/

/**
 * 配置类
 */

@Configuration
public class AppConfigTest3 {
    @Bean
    public FruitFactoryBean fruitFactoryBean(){
        return new FruitFactoryBean();
    }
}
