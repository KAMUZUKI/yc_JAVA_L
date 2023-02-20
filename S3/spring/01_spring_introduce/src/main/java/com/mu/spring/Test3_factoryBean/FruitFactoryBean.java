package com.mu.spring.Test3_factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 17:21
 **/

public class FruitFactoryBean implements FactoryBean<Pear> {

    public FruitFactoryBean() {
        System.out.println("FruitFactoryBean init");
    }

    @Override
    public Pear getObject() throws Exception {
        //加入逻辑
        return new Pear();
    }

    @Override
    public Class<?> getObjectType() {
        return Pear.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
