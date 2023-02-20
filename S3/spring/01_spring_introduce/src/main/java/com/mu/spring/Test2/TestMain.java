package com.mu.spring.Test2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 17:14
 **/

public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        Apple apple = (Apple)ac.getBean("apple");
        System.out.println(apple);

        ThreadPoolExecutor tpe = (ThreadPoolExecutor)ac.getBean("tpe");
        tpe.submit(()->{
            System.out.println("hello");
        });
    }
}
