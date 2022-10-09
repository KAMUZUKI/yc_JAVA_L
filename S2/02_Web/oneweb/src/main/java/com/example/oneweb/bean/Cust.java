package com.example.oneweb.bean;

public class Cust {
    private Long uid;
    private String uname;
    private String pwd;
    private String sex;
    private String favours;
    private String comments;
    private String hometown;
    private Integer age;    //Integer对象(null/数字，方法)  int基本类型数据(数字)
    private Double weight;

    @Override
    public String toString() {
        return "Cust{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", pwd='" + this.getEncPwd() + '\'' +
                ", sex='" + sex + '\'' +
                ", favours='" + favours + '\'' +
                ", comments='" + comments + '\'' +
                ", hometown='" + hometown + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }

    public String getEncPwd(){
        //逻辑
        return "*****";
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public Double getWeight() {
        return weight;
    }

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

