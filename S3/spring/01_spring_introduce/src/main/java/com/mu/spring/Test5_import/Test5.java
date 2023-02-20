package com.mu.spring.Test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 18:16
 **/

public class Test5 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig_Test5.class);

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        Banana banana = (Banana) context.getBean("com.mu.spring.Test5_import.Banana");
        System.out.println(banana);

        Banana banana2 = (Banana) context.getBean(Banana.class);
        System.out.println(banana2);
    }
}
