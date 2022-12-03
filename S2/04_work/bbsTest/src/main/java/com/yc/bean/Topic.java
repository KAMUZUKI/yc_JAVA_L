package com.yc.bean;

import java.util.Date;

public class Topic {
    //数据库tiopic表中有的
    private int topicid;
    private String title;
    private String content;
    private String publishtime;
    private String modifytime;
    private int uid;
    private int boardid;

    //从数据库查出来的,界面需要的
    private int total;//这个帖子的回复数量
    private String uname;

    private String parentname;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }


    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int toppicid) {
        this.topicid = toppicid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishtiome() {
        return publishtime;
    }

    public void setPublishtiome(String publishtiome) {
        this.publishtime = publishtiome;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }
}
