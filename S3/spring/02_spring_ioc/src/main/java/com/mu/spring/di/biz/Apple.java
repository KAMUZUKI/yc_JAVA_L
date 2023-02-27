package com.mu.spring.di.biz;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 16:45
 **/

@Component
public class Apple implements InitializingBean, DisposableBean {
    public Apple() {
        System.out.println("Apple Constructor");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Apple init");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Apple destroy");
    }
}
