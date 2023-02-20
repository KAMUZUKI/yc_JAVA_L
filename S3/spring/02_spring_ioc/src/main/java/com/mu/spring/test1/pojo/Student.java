package com.mu.spring.test1.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author : MUZUKI
 * @date : 2023-02-20 19:21
 **/

@Component("student")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
public class Student {
    @Value("10000")
    private Integer id;

    @Value("muzuki")
    private String name;
}
