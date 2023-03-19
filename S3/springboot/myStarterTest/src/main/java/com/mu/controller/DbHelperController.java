package com.mu.controller;

import com.mu.dbhelperautoconfiguration.dbhelper.DbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : MUZUKI
 * @date : 2023-03-12 11:09
 **/

@RestController
public class DbHelperController {
    @Autowired
    DbHelper dbHelper;

    @RequestMapping("/dbhelper")
    public String showDbHelper(){

        return dbHelper.showDbHelper();
    }
}
