package com.mu.bean;

import java.io.Serializable;
import java.util.Objects;

public class Board implements Serializable {//在服务器端的java bean，为了让tomcat能缓存数据，最好实现Serializable
    private Integer  boardid;
    private String boardname;
    private  Integer parentid;

    private Integer topicnum;
    private String uname;
    private String title;
    private String modifytime;
    private Integer topicid;

    @Override
    public int hashCode() {
        return Objects.hash(boardid);
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) {
            return true;
        }
        if(o==null||getClass()!=o.getClass()) {
            return false;
        }
        Board board=(Board)o;
        return boardid.equals(board.boardid);
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardid=" + boardid +
                ", boardname='" + boardname + '\'' +
                ", parentid=" + parentid +
                ", topicnum=" + topicnum +
                ", uname='" + uname + '\'' +
                ", title='" + title + '\'' +
                ", modifytime='" + modifytime + '\'' +
                ", topicid=" + topicid +
                '}';
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Integer getBoardid() {
        return boardid;
    }

    public void setBoardid(Integer boardid) {
        this.boardid = boardid;
    }

    public String getBoardname() {
        return boardname;
    }

    public void setBoardname(String boardname) {
        this.boardname = boardname;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getTopicnum() {
        return topicnum;
    }

    public void setTopicnum(Integer topicnum) {
        this.topicnum = topicnum;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }



    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }
}
