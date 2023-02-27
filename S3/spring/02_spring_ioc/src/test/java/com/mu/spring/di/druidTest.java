package com.mu.spring.di;

import com.mu.spring.di.biz.StudentBizImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 15:27
 **/

public class druidTest {
    @Test
    public void test1(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentBizImpl sbi = ac.getBean(StudentBizImpl.class);
        sbi.add("zhangsan");
        ((AnnotationConfigApplicationContext) ac).close();
    }

    @Test
    public void druidTest() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        System.out.println(dataSource.getConnection());
    }
}
