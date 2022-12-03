package com.mu.bean;

import lombok.Data;
import java.io.Serializable;

@Data
public class Topic implements Serializable {//在服务器端的java bean，为了让tomcat能缓存数据，最好实现Serializable
    private Integer topicid;
    private String title;
    private String content;
    private String publishtime;
    private String modifytime;
    private Integer uid;
    private Integer boardid;

    private String uname;
    //private String head;
    //private String regtime;
    private String total="0";

    public String getTotal() {
        if(this.total==null){
            return "0";
        }
        return total;
    }
}
