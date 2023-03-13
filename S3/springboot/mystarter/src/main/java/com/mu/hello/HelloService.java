package com.mu.hello;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : MUZUKI
 * @date : 2023-03-11 20:45
 **/

public class HelloService {
    /**
     * 此业务类所需的属性配置
     */
    @Autowired
    private HelloProperties helloProperties;

    public String sayHello( String userName){
        return helloProperties.getPrefix()+":"+userName+">>"+helloProperties.getSuffix();
    }
}