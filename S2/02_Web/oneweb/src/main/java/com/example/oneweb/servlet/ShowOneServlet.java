package com.example.oneweb.servlet;

import com.example.oneweb.bean.Cust;
import com.example.oneweb.servlet.dao.DBHelper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet(name = "ShowOneServlet", value = "/showOne.action")
public class ShowOneServlet extends CommonServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取参数的方式一：
        //String uid = request.getParameter("uid");
        //取参数的方式二
        Cust c= null;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            c=super.parseRequestToT(request, Cust.class);
            Long uid = c.getUid();
            DBHelper db = new DBHelper();
            List<Cust> list=db.select("select * from cust where uid=?", Cust.class, uid+"");
            Cust cust =null;
            out.println("<ul>");
            if (list!=null&&list.size()>0){
                cust=list.get(0);
                out.println("<li>"+cust+"</li>");
            }else {
                out.println("<li>"+"查无此人"+"</li>");
            }
            out.println("</ul>");
        } catch (Exception e) {
           e.printStackTrace();
           out.println(e.getMessage());
        }
        out.flush();
    }
}
