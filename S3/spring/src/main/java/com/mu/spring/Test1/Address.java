package com.mu.spring.Test1;

import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 17:03
 **/

@Data
public class Address {
    private String province;
    private String city;

    public Address() {
        System.out.println("Address constructor");
    }
}
