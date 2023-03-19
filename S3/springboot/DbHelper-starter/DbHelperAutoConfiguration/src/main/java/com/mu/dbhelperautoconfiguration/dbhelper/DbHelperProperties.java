package com.mu.dbhelperautoconfiguration.dbhelper;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : MUZUKI
 * @date : 2023-03-12 10:32
 **/

@ConfigurationProperties("com.mu.dbhelperautoconfiguration")
public class DbHelperProperties {
    private String url;

    private String username;

    private String password;

    private String driverClassName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
