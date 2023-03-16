package com.mu.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MUZUKI
 * @Classname PicController
 * @Description TODO
 * @Date 2023/3/16 22:16
 */

@RestController
public class PicController {
    @RequestMapping("//1.png")
    public String pic() {
        return "pic";
    }
}
