package com.mu.hello;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性文件的绑定类  :需要被  spring托管
 * @author : MUZUKI
 * @date : 2023-03-11 20:44
 */

@Component
@ConfigurationProperties("com.hello")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
}