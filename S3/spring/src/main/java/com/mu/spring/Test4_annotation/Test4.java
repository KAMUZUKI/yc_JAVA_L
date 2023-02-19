package com.mu.spring.Test4_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 18:02
 **/

public class Test4 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig_Test4.class);

        // beanid 的约定是：类名首字母小写
//        ProductBiz productBiz = (ProductBiz) context.getBean("productBizImpl");
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
//        productBiz.takein();
    }
}
