package com.mu.controller;

import com.mu.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : MUZUKI
 * @date : 2023-03-11 15:38
 **/

@Controller
@Slf4j
public class HelloController {

    @Autowired
    private Environment env;

    @Autowired
    private ConfigurableEnvironment cEnv;

    @Autowired
    private Product product;

    @Value("${product.pname}")
    private String pname;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        log.info(pname);
        log.info(product.toString());

        log.info("profile: " + env.getProperty("product.pname"));
        log.info("sys property" + cEnv.getSystemProperties().toString());
        log.info("sys env" + cEnv.getSystemEnvironment().toString());
        return "hello";
    }
}
