package com.yc.bean;

import java.util.Objects;

public class Board {
    private int boardid;
    private String boardname;
    private int parentid;

    private int total;
    private int topicid;
    private String title;
    private String modifytime;
    private String uname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null || getClass() != o.getClass())) return false;
        Board board = (Board) o;
        return boardid == board.boardid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoardid());
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardid=" + boardid +
                ", boardname='" + boardname + '\'' +
                ", parentid=" + parentid +
                ", total=" + total +
                ", topicoid=" + topicid +
                ", title='" + title + '\'' +
                ", modifytime='" + modifytime + '\'' +
                ", uname='" + uname + '\'' +
                '}';
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

    public String getBoardname() {
        return boardname;
    }

    public void setBoardname(String boardname) {
        this.boardname = boardname;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicoid) {
        this.topicid = topicoid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
