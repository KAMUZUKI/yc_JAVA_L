package com.mu.TimeAutoConfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : MUZUKI
 * @date : 2023-03-12 09:14
 **/

@ConfigurationProperties("com.mu")
public class TimeProperties {
    private String format = "yyyy-MM-dd HH:mm:ss";

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
