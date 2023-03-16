package com.mu.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author MUZUKI
 * @Classname TestDemo
 * @Description TODO
 * @Date 2023/3/7 17:03
 */

@Data
@Component
public class TestDemo {
    @Value("test1")
    private String host;

    public String getHost() {
        return host;
    }
}
