package com.mu.spring.test1;

import com.mu.spring.test1.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : MUZUKI
 * @date : 2023-02-20 19:23
 **/

public class spring_ioc_test1 {
    @Test
    public void test1() {
        // 1. 创建Spring的IOC容器对象
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        // 2. 从IOC容器中获取Bean实例
        Student student = (Student) context.getBean("student");
        // 3. 使用Bean
        System.out.println(student);
    }
}
