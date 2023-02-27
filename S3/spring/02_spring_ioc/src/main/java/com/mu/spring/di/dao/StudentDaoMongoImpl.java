package com.mu.spring.di.dao;

import org.springframework.stereotype.Controller;

/**
 * @author MUZUKI
 */

@Controller
public class StudentDaoMongoImpl {
    public void add(String name) {
        System.out.println("add student: " + name);
    }
}