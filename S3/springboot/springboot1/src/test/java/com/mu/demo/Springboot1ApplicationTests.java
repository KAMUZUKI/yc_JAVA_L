package com.mu.demo;

import com.mu.ApplicationStarter;
import com.mu.bean.Pet;
import com.mu.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationStarter.class})
@Slf4j
class Springboot1ApplicationTests {

    @Autowired
    private Pet pet;

    @Autowired
    private Product product;

    @Test
    public void test(){
        log.info(pet.toString());
    }

    @Test
    public void test2(){
        log.info(product.toString());
    }

}
