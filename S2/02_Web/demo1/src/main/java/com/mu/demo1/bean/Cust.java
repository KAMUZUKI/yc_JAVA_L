package com.mu.demo1.bean;

public class Cust {
    private Long uid;
    private String uname;
    private String pwd;
    private String sex;
    private String favours;
    private String comments;
    private String hometown;

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setFavours(String favours) {
        this.favours = favours;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public Long getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getPwd() {
        return pwd;
    }

    public String getSex() {
        return sex;
    }

    public String getFavours() {
        return favours;
    }

    public String getComments() {
        return comments;
    }

    public String getHometown() {
        return hometown;
    }
}

