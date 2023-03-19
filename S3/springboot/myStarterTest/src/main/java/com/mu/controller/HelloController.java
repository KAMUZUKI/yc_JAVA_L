package com.mu.controller;

import com.mu.helloautoconfiguration.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : MUZUKI
 * @date : 2023-03-11 20:54
 **/

@RestController
public class HelloController {

    @Autowired
    private Time time;

    @GetMapping("/showTime")
    public String sayHello(@RequestParam String name){
        return time.showTime(name);
    }
}