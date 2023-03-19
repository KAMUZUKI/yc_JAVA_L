package com.mu.helloautoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : MUZUKI
 * @date : 2023-03-12 09:15
 **/

public class Time {
    @Autowired
    private TimeProperties timeProperties;

    public String showTime(String name){
        String format = timeProperties.getFormat();
        DateFormat dateFormat = new SimpleDateFormat(format);
        String t = dateFormat.format(new Date());
        return "Hello " + name + ", now is " + t;
    }
}
