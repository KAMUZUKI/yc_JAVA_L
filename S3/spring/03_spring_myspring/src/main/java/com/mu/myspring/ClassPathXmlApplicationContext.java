package com.mu.myspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : MUZUKI
 * @date : 2023-02-22 20:19
 **/

public class ClassPathXmlApplicationContext implements ApplicationContext{

    private final Logger logger = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);


    public ClassPathXmlApplicationContext(){

    }

    @Override
    public Object getBean(String name) {
        return null;
    }
}
