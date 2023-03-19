package com.mu.springmvc;

import com.mu.springmvc.dao.StuMapper;
import com.mu.springmvc.domain.Stu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class SpringMvcApplicationTests {
    @Autowired(required = false)
    private StuMapper stuMapper;


    @Test
    void contextLoads() {
        List<Stu> list = stuMapper.selectList(null);
        log.info("" + list.size());
        for (Stu stu : list) {
            log.info(stu.toString());
        }
    }

    @Test
    public void testInsert(){
        Stu s = new Stu();

        s.setHead("head_1");
        s.setLifes("life_1,life_2");
        s.setPwd("123");
        s.setUname("zhangsan");
        int res = stuMapper.insert(s);
        log.info("res:{}",res);
    }

}
