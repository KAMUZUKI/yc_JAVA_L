package com.mu.logaopproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : MUZUKI
 * @date : 2023-03-12 15:50
 **/

@ConfigurationProperties(prefix = "mu.log")
public class LogProperties {
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
