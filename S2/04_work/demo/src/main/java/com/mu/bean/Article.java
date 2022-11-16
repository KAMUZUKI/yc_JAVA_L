package com.mu.bean;



/**
 * @author : MUZUKI
 * @date : 2022-11-06 19:16
 * @description : 文章实体类
 **/


public class Article {
    /**
     * 文章id
     */
    private Integer id;
    /**
     * 作者
     */
    private String author;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 关键字 用于词云快速浏览
     */
    private String keyWords;
    /**
     * 描述
     */
    private String description;
    /**
     * 栏目
     */
    private Integer categoryId;
    /**
     * 标签，用逗号分隔
     */
    private String label;
    /**
     * 标题图片
     */
    private String titleImgs;
    /**
     * 状态 0：草稿 1：发布
     */
    private String status;
    /**
     * 发布时间
     */
    private String createTime;
    /**
     * 阅读量
     */
    private Integer readCnt;
    /**
     * 点赞数
     */
    private Integer agreeCnt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitleImgs() {
        return titleImgs;
    }

    public void setTitleImgs(String titleImgs) {
        this.titleImgs = titleImgs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getReadCnt() {
        return readCnt;
    }

    public void setReadCnt(Integer readCnt) {
        this.readCnt = readCnt;
    }

    public Integer getAgreeCnt() {
        return agreeCnt;
    }

    public void setAgreeCnt(Integer agreeCnt) {
        this.agreeCnt = agreeCnt;
    }
}
