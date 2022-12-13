package com.mu.web.servlet;

import com.mu.biz.ReplyBiz;
import com.mu.web.model.JsonModel;
import com.mu.web.model.Pagebean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReplyServlet",value="/reply.action")
public class ReplyServlet extends CommonServlet {
    protected void showReplyBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm=new JsonModel();
        String topicid = request.getParameter("topicid");
        Pagebean pb=null;
        try {
            pb=super.parseRequestToT(request,Pagebean.class);
            ReplyBiz rbiz=new ReplyBiz();
            pb = rbiz.pageSearch(pb, topicid);

            jm.setCode(1);
            jm.setData(pb);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg("查询失败，原因："+e.getMessage());
        }
        super.writeJson(jm,response);


    }


}
