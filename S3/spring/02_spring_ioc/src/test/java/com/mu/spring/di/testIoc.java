package com.mu.spring.di;

import com.mu.spring.di.biz.Apple;
import com.mu.spring.di.biz.StudentBizImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 17:09
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class testIoc {
    @Autowired
    private Apple apple;

    @Autowired
    private StudentBizImpl sbi;

    @Test
    public void test1(){
        System.out.println(apple);
    }

    @Test
    public void test2(){
        sbi.add("zhangsan");
    }
}
