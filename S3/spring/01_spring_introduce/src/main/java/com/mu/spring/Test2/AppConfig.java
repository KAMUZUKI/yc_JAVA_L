package com.mu.spring.Test2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 17:13
 **/

@Configuration
public class AppConfig {
    @Bean
    public Apple apple(){
        Apple apple = new Apple();
        apple.setId(100);
        return new Apple();
    }

    @Bean
    public ThreadPoolExecutor tpe(){
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPoolSize = corePoolSize * 2;
        long keepAliveTime = 10;
        TimeUnit unit =TimeUnit.SECONDS;
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(maxPoolSize*4);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,unit,queue);
        executor.prestartAllCoreThreads();
        return executor;
    }
}
