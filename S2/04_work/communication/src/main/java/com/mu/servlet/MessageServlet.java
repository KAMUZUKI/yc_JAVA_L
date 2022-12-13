package com.mu.servlet;

import com.mu.bean.DataModel;
import com.mu.bean.JsonModel;
import com.mu.bean.Resuser;

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
    private int id;

    protected void getAllMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        HttpSession session =req.getSession();
        LinkedList<DataModel> linkList =new LinkedList<DataModel>();
        ServletContext application = session.getServletContext();
        if(application.getAttribute("message")!=null){
            linkList=(LinkedList<DataModel>)application.getAttribute("message");
        }
        jm.setCode(1);
        jm.setData(linkList);

        writeJson(jm,resp);
    }

    protected void sendMessageOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取用户输入的信息
        String message = request.getParameter("message");

        DataModel model = new DataModel();
        //获取当前登录的用户
        HttpSession session = request.getSession();
        Resuser rs = (Resuser) session.getAttribute("resuser");
        String username = rs.getUsername();
        //{id:'1',sender:"zhangsan",data:"Hello World1"}
        model.setId(id++);
        model.setSender(username);
        model.setData(message);
        model.setDate(new Date().toString());

        List<DataModel> linkedList = new LinkedList<DataModel>();
        ServletContext application = session.getServletContext();
        if (application.getAttribute("message") != null) {
            linkedList = (List<DataModel>) application.getAttribute("message");
        }
        ((LinkedList<DataModel>) linkedList).addFirst(model);
        application.setAttribute("message", linkedList);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCode(1);
        jsonModel.setData(linkedList);
        super.writeJson(jsonModel, response);
    }
}
