package com.mu;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 14:05
 **/

public class loggerTest {
    private static final Logger logger = LoggerFactory.getLogger(loggerTest.class);

    @Test
    public void test(){
        logger.info("info logger");
        logger.error("error logger");
        logger.debug("debug logger");
    }
}
