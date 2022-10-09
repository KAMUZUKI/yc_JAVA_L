package com.mu.bean;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 09:20
 * @description : 用户信息
 **/

public class Resuser {
    private String username;
    private String pwd;
    private String valcode;


    public Resuser() {
    }

    public Resuser(String username, String pwd, String valcode) {
        this.username = username;
        this.pwd = pwd;
        this.valcode = valcode;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public String getValcode() {
        return valcode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setValcode(String valcode) {
        this.valcode = valcode;
    }

    @Override
    public String toString() {
        return "Resuser{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", valcode='" + valcode + '\'' +
                '}';
    }
}
