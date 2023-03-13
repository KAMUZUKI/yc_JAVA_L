package com.mu.hello;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : MUZUKI
 * @date : 2023-03-11 20:47
 **/

@Configuration
@ConditionalOnMissingBean( HelloService.class)   //只有容器中还没有HelloService的bean时，才自动创建
@EnableConfigurationProperties(HelloProperties.class)   //将HelloProperties托管
public class HelloServiceAutoConfiguration {
    /**
     * 将业务类托管
     */
    @Bean
    public  HelloService HelloService(){
        HelloService hs=new HelloService();
        return hs;
    }
}

