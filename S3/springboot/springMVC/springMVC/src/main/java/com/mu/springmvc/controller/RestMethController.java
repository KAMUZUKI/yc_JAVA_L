package com.mu.springmvc.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author MUZUKI
 * @Classname RestMethController
 * @Description TODO
 * @Date 2023/3/16 22:26
 */

@RestController
public class RestMethController {

    @GetMapping("/rest")
    public String restGet() {
        return "restGet";
    }

    @PostMapping("/rest")
    public String restPost() {
        return "restPost";
    }

    @DeleteMapping("/rest")
    public String restDelete() {
        return "restDelete";
    }

    @PutMapping("/rest")
    public String restPut(){
        return "restPut";
    }
}
