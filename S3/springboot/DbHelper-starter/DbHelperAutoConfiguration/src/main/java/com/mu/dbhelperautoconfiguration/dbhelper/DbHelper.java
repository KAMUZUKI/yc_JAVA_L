package com.mu.dbhelperautoconfiguration.dbhelper;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : MUZUKI
 * @date : 2023-03-12 10:40
 **/

public class DbHelper {
    @Autowired
    private DbHelperProperties dbHelperProperties;

    public String showDbHelper(){

        return "url: " + dbHelperProperties.getUrl() + ", username: " + dbHelperProperties.getUsername() + ", password: " + dbHelperProperties.getPassword() + ", driverClassName: " + dbHelperProperties.getDriverClassName();
    }
}
