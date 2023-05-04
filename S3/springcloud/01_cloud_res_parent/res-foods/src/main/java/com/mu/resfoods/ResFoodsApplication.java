package com.mu.resfoods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : MUZUKI
 * @date : 2023-04-08 17:11
 **/

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.mu.resfoods.dao")
public class ResFoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResFoodsApplication.class, args);
    }
}
