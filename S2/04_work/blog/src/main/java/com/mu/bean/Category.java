package com.mu.bean;



/**
 * @author : MUZUKI
 * @date : 2022-11-06 19:23
 * @description : 栏目实体类
 **/

public class Category {
    /**
     * 栏目id
     */
    private Integer id;
    /**
     * 栏目名称
     */
    private String name;
    /**
     * 栏目排序
     */
    private Integer sort;
    /**
     * 栏目说明
     */
    private String introduce;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
