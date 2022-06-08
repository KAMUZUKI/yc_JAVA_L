package com.mu.config;

import com.mu.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class userConfig2 {
    @Bean
    public User user() {
        return new User();
    }
}
