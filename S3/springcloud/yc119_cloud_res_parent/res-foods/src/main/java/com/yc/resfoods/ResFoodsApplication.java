package com.yc.resfoods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.yc.resfoods.dao")
@EnableDiscoveryClient   //启用服务注册发现的客户端:  httpclient/postman
public class ResFoodsApplication {

    public static void main(String[] args) {

        SpringApplication.run(ResFoodsApplication.class, args);
    }

}
