package com.mu.dao;

import redis.clients.jedis.Jedis;

/**
 * @author : MUZUKI
 * @date : 2022-10-22 14:59
 **/

public class RedisHelper {
    public static Jedis getRedisInstance(){
        YamlReader yamlReader = YamlReader.instance;
        Jedis jedis = new Jedis((String) yamlReader.getValueByKey("config.ip"),(Integer) yamlReader.getValueByKey("config.port"));
        jedis.auth("123456");
        jedis.select(1);
        return jedis;
    }
}
