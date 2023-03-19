package com.mu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MUZUKI
 * @Classname PicController
 * @Description TODO
 * @Date 2023/3/16 22:16
 */

@Controller
public class PicController {
    @RequestMapping("/1.png")
    public String pic() {
        return "pic";
    }

    @RequestMapping("/index")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/reg")
    public String upload() {
        return "reg.html";
    }

    @RequestMapping("/test")
    public String test() {
        return "test3.html";
    }
}
