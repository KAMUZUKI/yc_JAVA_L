package com.mu.web.servlet;

import com.mu.bean.Article;
import com.mu.bean.Category;
import com.mu.bean.Comment;
import com.mu.bean.Flink;
import com.mu.dao.DbHelper;
import com.mu.dao.RedisHelper;
import com.mu.utils.Constants;
import com.mu.web.model.JsonModel;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InfoServlet", value = "/info.action")
public class InfoServlet extends CommonServlet {
    //info.action?op=getAritcleById
    protected void getArticleById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DbHelper db = new DbHelper();
        JsonModel jm = new JsonModel();
        Article article = new Article();
        Jedis jedis = RedisHelper.getReadisInstance();
        int categoryId;
        List<Article> list = null;
        String sql = "select * from article where id=?";

        try {
            article=super.parseRequestToT(request,Article.class);
            list = db.select(sql, Article.class, article.getId());
            if (list.size() > 0) {
                jm.setCode(1);
                jm.setData(list.get(0));
                super.writeJson(jm, response);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(0);
        jm.setMsg("查无此文章...");
        super.writeJson(jm, response);
    }



    //info.action?op=getCategory
    protected void getCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        String sql = "select * from category";
        List<Category> list = new ArrayList<Category>();
        try {
            list = db.select(sql, Category.class);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(list);
        super.writeJson(jm, response);
    }

    //info.action?op=getFlink
    protected void getFlink(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        Flink flink = new Flink();
        List<Flink> list = new ArrayList<Flink>();
        String sql = "select * from flink";
        try {
            list = db.select(sql, Flink.class);
            if (list == null || list.size() <= 0) {
                jm.setCode(0);
                jm.setMsg("获取失败");
                super.writeJson(jm, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(list);
        super.writeJson(jm, response);
    }


    //info.action?op=addComments
    protected void addComments(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        Comment comment = new Comment();
        String sql = "insert into comment values(null,?,?,?,?)";
        int result = 0;
        try {
            comment = super.parseRequestToT(request, Comment.class);
            result = db.doUpdata(sql, comment.getArticleId(), comment.getContent(), comment.getCreateBy(), comment.getCreateTime());
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        if (result == 0) {
            jm.setCode(0);
            jm.setMsg("评论失败");
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(comment);
        super.writeJson(jm, response);
    }

    //info.action?op=getAllCategory
    protected void getAllCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        String sql = "select * from category";
        Category category = new Category();
        try {
            category = super.parseRequestToT(request, Category.class);
            DbHelper db = new DbHelper();
            List<Category> list = db.select(sql, Category.class);
            if (list != null && list.size() > 0) {
                List<String> liststr = new ArrayList<String>();
                for (Category c : list) {
                    liststr.add(c.getName());
                }
                jm.setCode(1);
                jm.setData(liststr);
            } else {
                jm.setCode(0);
                jm.setMsg("没查到");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        super.writeJson(jm, response);
    }


    //info.action?op=getAllTags
    protected void getAllTags(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        Article article = new Article();
        try {
            article = super.parseRequestToT(request, Article.class);
            HttpSession session = request.getSession();
            String sql = "select distinct keyWords from article";
            List<Article> list = db.select(sql, Article.class);
            if (list != null && list.size() > 0) {
                List<String> liststr = new ArrayList<String>();
                for (Article a : list) {
                    liststr.add(a.getKeyWords());
                }
                jm.setCode(1);
                jm.setData(liststr);
            } else {
                jm.setCode(0);
                jm.setMsg("没查到");
            }
        } catch (Exception e) {
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        super.writeJson(jm, response);
    }

    //info.action?op=addArticle
    protected void addArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        Article article = new Article();
        int result = 0;
        try {
            article = super.parseRequestToT(request, Article.class);
            String sql = "insert into article(author,title,content,KeyWords,description,categoryId,label,titleImgs,status,createTime,readCnt,agreeCnt)" +
                    " values(?,?,?,?,?,?,?,?,?,?,'0','0')";
            result = db.doUpdata(sql, article.getAuthor(), article.getTitle(), article.getContent(),
                    article.getKeyWords(), article.getDescription(), article.getCategoryId(), article.getLabel(),
                    article.getTitleImgs(), article.getStatus(), article.getCreateTime());
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        if (result == 0) {
            jm.setCode(0);
            jm.setMsg("发布失败");
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        super.writeJson(jm, response);
    }


    //info.action?op=getAllAritcle
    protected void getAllArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {



        DbHelper db = new DbHelper();
        JsonModel jm = new JsonModel();
        Article article = new Article();
        Jedis jedis = RedisHelper.getReadisInstance();
        int categoryId;
        List<Article> list = null;
        String sql = "select * from article order by readCnt desc";
        try {
            list = db.select(sql, Article.class);
            if (list == null && list.size() <= 0) {
                jm.setCode(0);
                jm.setMsg("无数据...");
                super.writeJson(jm, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(list);
        super.writeJson(jm, response);
    }


    //info.action?op=getByCategory
    protected void getByCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DbHelper db = new DbHelper();
        JsonModel jm = new JsonModel();
        Article article = new Article();
        Jedis jedis = RedisHelper.getReadisInstance();
        int categoryId;
        List<Article> list = null;
        String sql = "select id,author,title,content,keyWords,description,categoryId,label,titleImgs,status,createTime,readCnt,agreeCnt from article where categoryId=?";

        try {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
            list = db.select(sql, Article.class, categoryId);
            //取出每个对象，将redis中的浏览量和点赞数存进去
            if (list.size() > 0) {
                for (Article a : list) {
                    //如果文章的阅读量redis和点赞redis还没建成,则建一下并将article对象的设为0
                    if (!jedis.exists(a.getId() + Constants.REDIS_ARTICLE_READCNT)) {
                        jedis.set(a.getId() + Constants.REDIS_ARTICLE_READCNT, "0");
                    }
                    if (!jedis.exists(a.getId() + Constants.REDIS_ARTICLE_PRAISE)) {
                        jedis.sadd(a.getId() + Constants.REDIS_ARTICLE_PRAISE, "0");
                        jedis.srem(a.getId() + Constants.REDIS_ARTICLE_PRAISE, "0");
                    }
                    //将文章的阅读量和点赞数取出来
                    String readCnt = jedis.get(a.getId() + Constants.REDIS_ARTICLE_READCNT);
                    Long agreeCnt = jedis.scard(a.getId() + Constants.REDIS_ARTICLE_PRAISE);
                    //分别放到article对象的readCnt和agreeCnt
                    a.setReadCnt(Integer.parseInt(readCnt));
                    a.setAgreeCnt(agreeCnt.intValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(list);
        super.writeJson(jm, response);
    }


    //info.action?op=getComments
    protected void getComments(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DbHelper db = new DbHelper();
        JsonModel jm = new JsonModel();
        Jedis jedis = RedisHelper.getReadisInstance();
        String articleId1 = "";
        List<Comment> list;
        int articleId = 0;
        Comment comment = new Comment();
        String sql = "select articleId,comment.id,content,user.name createBy,head,comment.createTime" +
                "                from comment,user" +
                "                where comment.createBy=user.id and articleId=?" +
                "                order by comment.createTime desc;";
        try {
            articleId = Integer.parseInt(request.getParameter("articleId"));
            //将文章的浏览量+1
            //如果没有创建过这个文章的redis,就创建
            if (!jedis.exists(articleId + Constants.REDIS_ARTICLE_READCNT)) {
                jedis.set(articleId + Constants.REDIS_ARTICLE_READCNT, "0");
            }
            //浏览量+1
            jedis.incr(articleId + Constants.REDIS_ARTICLE_READCNT);

            list = db.select(sql, Comment.class, articleId);
            if (list.size() > 0 && list != null) {
                for (Comment c : list) {
                    c.setArticleId(articleId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(list);
        super.writeJson(jm, response);
    }
}
