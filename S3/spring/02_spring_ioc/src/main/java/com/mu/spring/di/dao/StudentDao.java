package com.mu.spring.di.dao;

import org.springframework.stereotype.Controller;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 15:16
 **/

@Controller
public class StudentDao {
    public void add(String name) {
        System.out.println("add student: " + name);
    }
}
