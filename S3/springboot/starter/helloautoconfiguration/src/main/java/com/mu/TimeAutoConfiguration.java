package com.mu;

import com.mu.helloautoconfiguration.Time;
import com.mu.helloautoconfiguration.TimeProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : MUZUKI
 * @date : 2023-03-12 09:18
 **/

@Configuration  //托管配置类
@ConditionalOnClass(Time.class)  //条件
@EnableConfigurationProperties(TimeProperties.class)  //属性类的bean
public class TimeAutoConfiguration {
    @Bean
    public Time time(){
        Time time = new Time();
        return time;
    }
}
