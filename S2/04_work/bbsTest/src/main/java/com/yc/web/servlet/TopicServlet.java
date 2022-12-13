package com.yc.web.servlet;

import com.yc.bean.*;
import com.yc.biz.DelAndModBiz;
import com.yc.biz.ShowIndex;
import com.yc.biz.getDetailDataBiz;
import com.yc.biz.getListDataBiz;
import com.yc.dao.DBHelper;
import com.yc.web.model.JsonModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TopicServlet", value = "/topic.action")
public class TopicServlet extends CommonServlet {


    //detail.html界面获取数据(话题和回复):op=getDetailData
    protected void getDetailData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm=new JsonModel();
        //查询单独的一个话题和这个话题的所有回复,将查询到的话题和回复封装成reply对象
        //创建一个Map里面放一个topic和一个pagebean
        Map map=new HashMap();
        String topicid = request.getParameter("topicid");
        String pagenostr = request.getParameter("pageno");
        int pageno=Integer.parseInt(pagenostr);
        getDetailDataBiz getdetaildatabiz=new getDetailDataBiz();
        //调用业务层里的方法查询topic
        Reply reply=new Reply();
       int topicid2=Integer.parseInt(topicid);
        reply=getdetaildatabiz.getTopic(topicid,reply);
        reply.setTopicid(topicid2);
        map.put("topic",reply);
        PageBean pb=new PageBean();
        pb.setPageno(pageno);
        // 将repaly对象放到pagebean的dataset[]里面,返回pagebean
        try {
            pb = getdetaildatabiz.getDetailData(pb, topicid);
            map.put("pageBean",pb);
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm,response);
            return;
        }
        jm.setCode(1);
        jm.setData(map);
        super.writeJson(jm,response);
    }

    //list.html界面获取数据:op=getListData
    protected void getListData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询所有的话题,将查询到的话题封装成topic对象
        String boardid = request.getParameter("boardid");
        String pagenostr = request.getParameter("pageno");
        //System.out.println(pagenostr);
        int pageno=Integer.parseInt(pagenostr);
        JsonModel jm = new JsonModel();
        PageBean pb =new PageBean();
        pb.setPageno(pageno);
        getListDataBiz getlistdatabiz = new getListDataBiz();
        try {
            pb = getlistdatabiz.findtopic(boardid, pb);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg("查询数据失败,原因:" + e.getMessage());
            super.writeJson(jm, response);
        }
        jm.setCode(1);
        jm.setData(pb);
        super.writeJson(jm, response);
    }

    //发帖子:op=sendtopic
    protected void sendTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            jm.setCode(0);
            jm.setMsg("请先登录...");
            super.writeJson(jm, response);
            return;
        }
        //取出参数中的title和content,存入一个Topic对象
        Topic topic = new Topic();
        try {
            topic = super.parseRequestToT(request, Topic.class);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            return;
        }
        topic.setUid(user.getUid());
        //插入数据库
        ShowIndex showIndex = new ShowIndex();
        int result = 0;
        try {
            result = showIndex.insertData(topic);
            //插进去之后再查一次,将最新的那个topic查出来
            String sql="select topicid from topic where publishtime=(select max(publishtime)  from topic)";
            DBHelper db=new DBHelper();
            List<Topic> list=db.select(sql,Topic.class);
            Topic topic1=new Topic();
            topic1=list.get(0);
            topic.setTopicid(topic1.getTopicid());
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        if (result == 0) {
            jm.setCode(0);
            jm.setMsg("数据库错误...");
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(topic);
        super.writeJson(jm, response);
    }

    //回复帖子:op=reply
    protected void reply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            jm.setCode(0);
            jm.setMsg("请先登录...");
            super.writeJson(jm, response);
            return;
        }
        //取出参数中的title和content,存入一个reply对象
        Reply reply = new Reply();
        try {
            reply = super.parseRequestToT(request, Reply.class);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            return;
        }
        reply.setUid(user.getUid());
        //插入数据库
        ShowIndex showIndex = new ShowIndex();
        int result = 0;
        try {
            result = showIndex.insertReplyData(reply);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        if (result == 0) {
            jm.setCode(0);
            jm.setMsg("数据库错误...");
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        super.writeJson(jm, response);
    }

    //删除帖子或回复:op=delete
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        String torr=request.getParameter("torr");
        String id=request.getParameter("id");
        DelAndModBiz dam=new DelAndModBiz();
        int flag=0;
        try{
             flag=dam.delete(torr,id);
        }catch (Exception e){
            jm.setCode(0);
            jm.setMsg("删除失败:"+e.getMessage());
            super.writeJson(jm,response);
            return;
        }
        if(flag==0){
            jm.setCode(0);
            jm.setMsg("删除失败!");
            super.writeJson(jm,response);
            return;
        }
        jm.setCode(flag);
        super.writeJson(jm,response);
    }

    //获取要修改的帖子或回复的信息:op=getmessage
    protected void getmessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        String torr = request.getParameter("torr");
        String id = request.getParameter("id");
        DBHelper db=new DBHelper();
        //DelAndModBiz dam = new DelAndModBiz();
        //如果要获取的是一个topic帖子
        if(torr.equals("topic")){
            Topic topic=new Topic();
            String sql="select * from topic where topicid=?";
            List<Topic> list=db.select(sql,Topic.class,id);
            if(list!=null&&list.size()>0){
                topic=list.get(0);
                jm.setCode(1);
                jm.setData(topic);
                super.writeJson(jm,response);
                return;
            }else{
                jm.setCode(0);
                jm.setData("无此话题!");
                super.writeJson(jm,response);
                return;
            }
        }
        //要获取的是一个reply回复
        else if(torr.equals("reply")){
            Reply reply=new Reply();
            String sql="select * from reply where replyid=?";
            List<Reply> list=db.select(sql,Reply.class,id);
            if(list!=null&&list.size()>0){
                reply=list.get(0);
                jm.setCode(2);
                jm.setData(reply);
                super.writeJson(jm,response);
                return;
            }else{
                jm.setCode(0);
                jm.setData("无此回复!");
                super.writeJson(jm,response);
                return;
            }
        }
    }

    //修改帖子或回复:op=modify
    protected void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        String torr=request.getParameter("torr");
        String id=request.getParameter("id");
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        DelAndModBiz dam=new DelAndModBiz();
        int flag=0;
        try{
            flag=dam.modify(torr,id,title,content);
        }catch (Exception e){
            jm.setCode(0);
            jm.setMsg("修改失败:"+e.getMessage());
            super.writeJson(jm,response);
            return;
        }
        if(flag==0){
            jm.setCode(0);
            jm.setMsg("修改失败!");
            super.writeJson(jm,response);
            return;
        }
        jm.setCode(flag);
        super.writeJson(jm,response);
    }

}
