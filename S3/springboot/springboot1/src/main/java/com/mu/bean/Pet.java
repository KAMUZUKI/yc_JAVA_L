package com.mu.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : MUZUKI
 * @date : 2023-03-11 15:22
 **/

@Component
@Data
@Slf4j
public class Pet {
    public Pet(){
        log.info("Pet constructor");
    }
}
