package com.mu.resfoods;

import com.mu.resfoods.biz.ResfoodBizImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : MUZUKI
 * @date : 2023-04-08 20:14
 **/

@SpringBootTest
public class ResfoodTest {
    @Autowired
    private ResfoodBizImpl resfoodBiz;

    @Test
    public void test() {
        System.out.println(resfoodBiz.findAll());
    }
}
