package com.mu.bean;

import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {//在服务器端的java bean，为了让tomcat能缓存数据，最好实现Serializable
    private Integer uid;
    private String uname;
    private String upass;
    private String head;
    private String regtime;
    private Integer gender;
}
