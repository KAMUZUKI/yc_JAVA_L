package com.mu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : MUZUKI
 * @date : 2023-04-09 10:05
 **/

@SpringBootApplication
@EnableFeignClients(basePackages ={"com.mu.api"} )
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
