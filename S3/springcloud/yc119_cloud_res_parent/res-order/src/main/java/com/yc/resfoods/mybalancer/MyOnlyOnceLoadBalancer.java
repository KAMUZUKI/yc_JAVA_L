package com.yc.resfoods.mybalancer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.SelectedInstanceCallback;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @program: yc119_cloud_res_parent
 * @description: 自定义的负载均衡策略.
 * @author: zy
 * @create: 2023-04-09 18:42
 */
public class MyOnlyOnceLoadBalancer implements ReactorServiceInstanceLoadBalancer{
    //服务列表
    ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
    public MyOnlyOnceLoadBalancer(  ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider  ){
        this.serviceInstanceListSupplierProvider=serviceInstanceListSupplierProvider;
    }
    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier=this.serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);

        return supplier.get(request).next().map((serviceInstances) -> {
            return processInstanceResponse(supplier, serviceInstances);
        });
    }
    //获取服务实例
    private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier,
                                                              List<ServiceInstance> serviceInstances) {
        Response<ServiceInstance> serviceInstanceResponse = getInstanceResponse(serviceInstances);
        if (supplier instanceof SelectedInstanceCallback && serviceInstanceResponse.hasServer()) {
            ((SelectedInstanceCallback) supplier).selectedServiceInstance(serviceInstanceResponse.getServer());
        }
        return serviceInstanceResponse;
    }
    //int i=0;  //多线程.
    //Map<ServiceInstance, Long> map;
    //从服务实例列表中取第一个实例
    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> serviceInstances) {
        if( serviceInstances.isEmpty()){
            return new EmptyResponse();
        }
        //取第一个
        ServiceInstance si=serviceInstances.get(0);
        //roundrobin:  ->    i++;
        // random  ->   random.nextInt(   serviceInstance.size() )
        //  按流量的权重:   ->　记录每个ＳｅｒｖｉｃｅＩｎｓｔａｎｃｅ的访问数量　，　取倒数．　
        return new DefaultResponse(si);
    }


}
