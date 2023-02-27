package com.mu;

import com.mu.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 21:10
 **/

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
public class Test1 {

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
//        System.out.println(userService);
        userService.save();
//        userService.saveTest();
    }
}
