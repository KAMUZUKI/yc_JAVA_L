package com.mu.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Reply implements Serializable {//在服务器端的java bean，为了让tomcat能缓存数据，最好实现Serializable
    private Integer replyid;
    private String title;
    private String  content;
    private String  publishtime;
    private String  modifytime;
    private Integer  uid;
    private Integer  topicid;

    private String  uname;
    private String  head;
    private String  regtime;
}


