package com.mu.logaopproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : MUZUKI
 * @date : 2023-03-12 16:14
 **/

@RestController
public class ProductController {
    @RequestMapping("/product")
    public String show(@RequestParam String pname){
        return "产品名：" + pname;
    }
}
