package com.yc.resfoods.configs;

import com.yc.resfoods.mybalancer.MyLoadBalancerConfig;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableDiscoveryClient    //启用服务发现的客户端

//给这个服务res-food服务指定负载均衡策略. 默认有两个策略:随机和轮询
//   LoadBalancerClientConfiguration类是系统提供.
//@LoadBalancerClient(name="res-food",configuration= LoadBalancerClientConfiguration.class)
//使用自定义的负载均衡
//@LoadBalancerClient(name="res-foods",configuration= MyLoadBalancerConfig.class)
//对多个服务绑定不同的负载均衡
@LoadBalancerClients(
        value={
                @LoadBalancerClient(  name="res-foods",configuration= MyLoadBalancerConfig.class),
                @LoadBalancerClient(  name="res-order",configuration= MyLoadBalancerConfig.class),
        },defaultConfiguration= LoadBalancerClientConfiguration.class
)
public class ApplicationConfig {


    @Bean
    @LoadBalanced     //负载平衡器: 一个服务名下有多个服务节点
    public RestTemplate restTemplate(    ){   //如此RestTemplate对象就有这个功能了..
        return new RestTemplate();
    }

//    @Bean
//    @LoadBalanced
//    public WebClient webClient(){
//        String baseUrl = "https://example.com";
//        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl)
//        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.TEMPLATE_AND_VALUES);
//// Customize the WebClient..
//        WebClient client = WebClient.builder().uriBuilderFactory(factory).build();
//        return client;
//    }
}
