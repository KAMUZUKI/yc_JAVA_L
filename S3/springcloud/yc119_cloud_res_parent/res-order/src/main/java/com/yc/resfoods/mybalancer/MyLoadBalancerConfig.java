package com.yc.resfoods.mybalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @program: yc119_cloud_res_parent
 * @description:
 * @author: zy
 * @create: 2023-04-09 19:02
 */
@Configuration
//@ConditionalOnDiscoveryEnabled
public class MyLoadBalancerConfig {
//    @Bean
//    //@ConditionalOnMissingBean
//    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment,
//                                                                                   LoadBalancerClientFactory loadBalancerClientFactory) {
//        //name指定的是服务名，这样这个服务就和一个负载均衡器绑定
//        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        return new MyOnlyOnceLoadBalancer(
//                loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class));
//    }

    @Bean
    //@ConditionalOnMissingBean
    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment,
                                                                                   LoadBalancerClientFactory loadBalancerClientFactory) {
        //res-foods 服务名
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RoundRobinLoadBalancer(
                loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }






}
