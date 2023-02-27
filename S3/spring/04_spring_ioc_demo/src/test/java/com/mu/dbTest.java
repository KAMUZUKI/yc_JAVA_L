package com.mu;

import com.mu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 17:22
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class dbTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        userService.save();
    }

    @Test
    public void test2(){
        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        }catch (Exception e) {}
    }
}
