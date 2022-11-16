package com.mu.bean;



/**
 * @author : MUZUKI
 * @date : 2022-11-06 19:26
 * @description : 评论实体类
 **/


public class Comment {
    /**
     * 评论id
     */
    private Integer id;
    /**
     * 评论文章id
     */
    private Integer articleId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论人
     */
    private String createBy;
    /**
     * 评论时间
     */
    private String createTime;
    /**
     * 评论人的头像
     */
    private String head;


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", head='" + head + '\'' +
                '}';
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
