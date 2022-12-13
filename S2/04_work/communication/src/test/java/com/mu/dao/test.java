package com.mu.dao;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class test {
    private int id;
    private String username;
    private String message;
    @Test
    public void test(){
        String str = "{id:'" + ++id + "',sender:'" + username + "',data:'" + message + "',date:'" + new Date() +"'}";
        System.out.println(str);
        String str1 = "{id:'" + ++id + "',sender:\"" + username + "\",data:\"" + message + "\",date:\"" + new Date() +"\"}";
        System.out.println(str1);
        String str2 = str1;
        System.out.println(str2);
    }
}
