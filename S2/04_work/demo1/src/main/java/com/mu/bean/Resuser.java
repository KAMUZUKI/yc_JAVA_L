package com.mu.bean;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 09:20
 * @description : 用户信息
 **/

public class Resuser {
    private Integer userid;
    private String username;
    private String pwd;
    private String valcode;


    public Resuser() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getValcode() {
        return valcode;
    }

    public void setValcode(String valcode) {
        this.valcode = valcode;
    }

    @Override
    public String toString() {
        return "Resuser{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", valcode='" + valcode + '\'' +
                '}';
    }
}
