package com.mu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
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
     * 内容
     */
    private String content;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 描述
     */
    private String description;

    /**
     * 栏目
     */
    private Integer categoryid;

    /**
     * 标签，用逗号分隔
     */
    private String label;

    /**
     * 标题图片列表
     */
    private String titleimgs;

    /**
     * 状态
     */
    private String status;

    /**
     * 发布时间
     */
    private Date createtime;

    /**
     * 阅读量
     */
    private Integer readcnt;

    /**
     * 点赞数
     */
    private Integer agreecnt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}