package com.mu.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : MUZUKI
 * @date : 2023-03-11 15:29
 **/
@Component
@Data
@ConfigurationProperties(prefix = "product")

public class Product {
    private String pname;
    private Double price;
    private Boolean isUsed;
    private Date manDate;

    private Map<String, String> attributes;

    private Address address;

    private List<String> types;
}
