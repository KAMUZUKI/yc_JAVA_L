package com.mu.spring.Test3_factoryBean;

import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 17:21
 **/

@Data
public class Pear {
    private int id;

    public Pear(){
        System.out.println("Pear init");
    }
}
