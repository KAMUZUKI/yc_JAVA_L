package com.mu.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : MUZUKI
 * @date : 2023-04-09 10:15
 **/

@Configuration
@EnableDiscoveryClient  // 开启服务发现
@LoadBalancerClients(
        value = {
                @LoadBalancerClient(name = "res-foods", configuration = MyLoadBalancerConfig.class),
                @LoadBalancerClient(name = "res-order", configuration = MyLoadBalancerConfig.class)
        },defaultConfiguration = LoadBalancerClientConfiguration.class
)
public class ApplicationConfig {
    @Bean
    @LoadBalanced // 开启负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
