package com.mu.web.servlet;

import com.mu.biz.TopicBiz;
import com.mu.dao.DbHelper;
import com.mu.web.model.JsonModel;
import com.mu.web.model.Pagebean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TopicServlet",value="/topic.action")
public class TopicServlet extends CommonServlet {
    protected void showTopicBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm=new JsonModel();
        String boardid = request.getParameter("boardid");
        Pagebean pb=null;
        try {
            pb=super.parseRequestToT(request,Pagebean.class);
            TopicBiz tbiz=new TopicBiz();
            pb = tbiz.pageSearch(pb, boardid);

            jm.setCode(1);
            jm.setData(pb);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg("查询失败，原因："+e.getMessage());
        }
        super.writeJson(jm,response);
    }

    protected void getTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm=new JsonModel();
        String topicid = request.getParameter("topicid");
        String sql="select topicid,title,content,\n" +
                "       date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,\n" +
                "       date_format(modifytime,'%Y-%m-%d %H:%I:%S') as  modifytime,\n" +
                "       tbl_topic.uid,\n" +
                "       uname,\n" +
                "       head,\n" +
                "       date_format(regtime,'%Y-%m-%d %H:%I:%S') as  regtime,\n" +
                "       boardid\n" +
                "from tbl_topic\n" +
                "         inner join tbl_user\n" +
                "                    on tbl_topic.uid=tbl_user.uid\n" +
                "where topicid=? ;";
        DbHelper db=new DbHelper();
        List<Map<String, Object>> list = db.select(sql, topicid);
        jm.setCode(1);
        jm.setData(list);


        super.writeJson(jm,response);
    }









}
