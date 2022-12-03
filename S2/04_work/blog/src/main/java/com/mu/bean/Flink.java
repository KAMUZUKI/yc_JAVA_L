package com.mu.bean;



/**
 * @author : MUZUKI
 * @date : 2022-11-06 19:28
 * @description : 友链实体类
 **/


public class Flink {
    /**
     * 友链id
     */
    private Integer id;
    /**
     * 友链名称
     */
    private String name;
    /**
     * 友链地址
     */
    private String url;
    /**
     * 友链图标
     */
    private String img;
    /**
     * 友链描述
     */
    private String description;
    /**
     * 友链状态
     */
    private String status;
    /**
     * 友链排序
     */
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
