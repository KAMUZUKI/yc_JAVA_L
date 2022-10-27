package com.mu.servlet;

import com.mu.bean.JsonModel;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-10-26 18:32
 **/

@WebServlet(name = "MessageServlet", value = "/message.action")
public class MessageServlet extends CommonServlet{
    protected void getAllMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        HttpSession session =req.getSession();
        LinkedList<String> linkList =new LinkedList<String>();
        ServletContext application = session.getServletContext();
        if(application.getAttribute("message")!=null){
            linkList=(LinkedList<String>)application.getAttribute("message");
        }
        jm.setCode(1);
        jm.setData(linkList);

        writeJson(jm,resp);
    }

    protected void sendMessageOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = 0;
        //获取用户输入的信息
        String message = request.getParameter("message");

        //获取当前登录的用户
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        //{id:'1',sender:"zhangsan",data:"Hello World1"}
        String str = "{id:'" + ++id + "',sender:\"" + username + "\",data:\"" + message + "\",date:\"" + new Date() +"\"}";
        System.out.println(str);

        List<String> linkedList = new LinkedList<String>();
        ServletContext application = session.getServletContext();
        if (application.getAttribute("message") != null) {
            linkedList = (List<String>) application.getAttribute("message");
        }
        ((LinkedList<String>) linkedList).addFirst(str);
        application.setAttribute("message", linkedList);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCode(1);
        super.writeJson(jsonModel, response);
    }
}
