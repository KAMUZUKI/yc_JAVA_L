package com.mu.servlet;

import com.mu.bean.Article;
import com.mu.dao.DbHelper;
import com.mu.dao.RedisHelper;
import com.mu.service.ArticleService;
import com.mu.utils.Constants;
import com.mu.web.model.JsonModel;
import com.mu.web.servlet.CommonServlet;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ArticleServlet", value = "/article.action")
public class ArticleServlet extends CommonServlet {
    @Autowired
    private ArticleService articleService;


    //article.action?op=deleteArticle
    protected void deleteArticle(HttpServletRequest request,HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        Article article = new Article();
        int result = 0;
        try{
            article = super.parseRequestToT(request,Article.class);
            result = articleService.deleteArticle(article.getId());;
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm,response);
            return;
        }
        if(result == 0){
            jm.setCode(0);
        }else if(result == 1){
            jm.setCode(1);
        }
        super.writeJson(jm,response);
    }


    //article.action?op=alterArticle
    protected void alterArticle(HttpServletRequest request,HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        Article article = new Article();
        String sql = "update article set author = ?,title = ?,content = ?,keyWords = ?,description = ?,categoryId = ?," +
                "label = ?,titleImgs = ?,status = ?,createTime = ? where id = ?";
        int result = 0;
        try{
            article = super.parseRequestToT(request,Article.class);
            result = db.doUpdata(sql,article.getAuthor(),article.getTitle(),article.getContent(),article.getKeyWords(),
                    article.getDescription(),article.getCategoryId(),article.getLabel(),article.getTitleImgs(),article.getStatus(),
                    article.getCreateTime(),article.getId());
        }catch(Exception e){
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            e.printStackTrace();
            super.writeJson(jm,response);
            return;
        }
        if(result==0){
            jm.setCode(0);
            jm.setMsg("更新失败");
        }else if(result == 1){
            jm.setCode(1);
            jm.setData(article);
        }
        super.writeJson(jm,response);
    }


    /**
     * article.action?op=changeData
     * 用来存redis,用户给文章点赞->文章对应的点赞数+1,取消赞->文章的点赞数-1,用户点击文章->文章浏览量+1
     * redis:
     * 浏览量:键key->readCnt:值value->ArticleId_
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void changeData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
//        long userpraiseCnt = 0;
//        long articlepraiseCnt = 0;
        try {
            String articleId = request.getParameter("articleId");
            String userId = request.getParameter("userId");
            Jedis jedis = RedisHelper.getReadisInstance();
            if (jedis.sismember(articleId + Constants.REDIS_ARTICLE_PRAISE, userId + "")) {
                //此用户已经对这篇文章点赞,再点就是取消
                //删除对文章点过赞的用户
                jedis.srem(articleId + Constants.REDIS_ARTICLE_PRAISE, userId + "");
                //删除用户点过赞的文章
                jedis.srem(userId + Constants.REDIS_USER_PRAISE, articleId + "");
            } else {
                //此用户没有对这篇文章点过赞
                //添加用户点过赞的文章
                jedis.sadd(userId + Constants.REDIS_USER_PRAISE, articleId + "");
                //添加文章被哪些用户点过赞
                jedis.sadd(articleId + Constants.REDIS_ARTICLE_PRAISE, userId + "");
            }
//            //查出数量
//            if (jedis.sismember(articleId + Constants.REDIS_ARTICLE_PRAISE, userId + "")) {
//                //用户对文章点赞数量
//                userpraiseCnt = jedis.scard(userId + Constants.REDIS_USER_PRAISE);
//                //文章被用户的点赞数量
//                articlepraiseCnt = jedis.scard(articleId + Constants.REDIS_ARTICLE_PRAISE);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        super.writeJson(jm, response);
    }



}
