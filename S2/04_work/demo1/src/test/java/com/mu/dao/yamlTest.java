package com.mu.dao;

import org.junit.jupiter.api.Test;

/**
 * @author : MUZUKI
 * @date : 2022-10-21 19:25
 **/

public class yamlTest {
    @Test
    public void test() {
        YamlReader yamlReader = YamlReader.instance;
        System.out.println(yamlReader.getValueByKey("config.ip"));
        System.out.println(yamlReader.getValueByKey("config.port"));
    }
}
