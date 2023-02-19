package com.mu.spring.Test3_factoryBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 17:21
 **/

public class Test {
    public static void main(String[] args) {
        //1.创建容器
        //容器启动： -> FruitFactoryBean
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigTest3.class);

        //beanid:fruitFactoryBean -> Pear 产品对象
        Object obj = context.getBean("fruitFactoryBean");
        System.out.println("obj  " + obj);
        System.out.println("obj.hashCode()  " + obj.hashCode());

        //beanid:&fruitFactoryBean -> Pear 工厂对象
        Object obj2 = context.getBean("&fruitFactoryBean");
        System.out.println("obj2  " + obj2);

        //2.pear是单例or多例
        //beanid:fruitFactoryBean -> Pear 产品对象
        obj = context.getBean("fruitFactoryBean");
        System.out.println("obj.hashCode()  " + obj.hashCode());

        //3. 获取spring容器中所有托管的bean

        String [] beanNames = context.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
    }
}
