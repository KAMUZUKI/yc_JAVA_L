package com.mu.spring.Test1;

import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 17:05
 **/

@Data
public class Student {
    private int id;
    private String name;

    private Address address;
    public Student(){
        System.out.println("Student constructor");
    }
}
