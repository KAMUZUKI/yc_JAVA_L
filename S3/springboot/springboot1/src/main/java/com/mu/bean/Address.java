package com.mu.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author : MUZUKI
 * @date : 2023-03-11 15:28
 **/

@Data
@Component
public class Address {
    private String province;
    private String city;
}
