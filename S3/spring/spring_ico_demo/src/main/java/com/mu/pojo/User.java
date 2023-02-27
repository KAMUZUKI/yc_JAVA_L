package com.mu.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 17:18
 **/

@Data
@Component
public class User {
    private String name;
    private String password;
}
