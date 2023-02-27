package com.mu.bean;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 10:09
 **/

@Data
public class Apple {
    private int id;

    private Logger logger = LoggerFactory.getLogger(Apple.class);

    public Apple() {
        logger.info("Apple的构造");
    }
}
