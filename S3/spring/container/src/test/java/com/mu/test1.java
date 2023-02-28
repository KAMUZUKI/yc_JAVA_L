package com.mu;

import com.mu.bean.Apple;
import org.junit.Test;
import org.muframework.annotation.ComponentScan;
import org.muframework.context.YcAnnotationConfigApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 11:19
 **/

public class test1 {

    private static final Logger logger = LoggerFactory.getLogger(test1.class);

    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = ClassLoader.getSystemClassLoader().loadClass(AppConfig.class.getName());
        Annotation annotation = clazz.getDeclaredAnnotation(ComponentScan.class);
        ComponentScan componentScan = (ComponentScan) annotation;
        String[] value = componentScan.value();
        for (String s : value) {
            System.out.println(s);
        }
    }

    @Test
    public void test2() throws ClassNotFoundException, IOException {
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources("com.mu");
        while(dirs.hasMoreElements()) {
            System.err.println(dirs.nextElement());
        }
    }

    @Test
    public void test3() throws ClassNotFoundException, IOException {
        YcAnnotationConfigApplicationContext context = new YcAnnotationConfigApplicationContext(AppConfig.class);

        Apple a = (Apple) context.getBean("apple");
        logger.info(a.toString());
        AppConfig appConfig = (AppConfig) context.getBean("appConfig");
        logger.info(appConfig.toString());
    }
}
