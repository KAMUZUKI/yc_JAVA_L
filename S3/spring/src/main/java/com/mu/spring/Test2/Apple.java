package com.mu.spring.Test2;

import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 17:14
 **/

@Data
public class Apple {
    private int id;

    public Apple(){
        System.out.println("Apple init");
    }
}
