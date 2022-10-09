package com.example.oneweb.servlet;

import com.example.oneweb.bean.Cust;
import com.example.oneweb.servlet.dao.DBHelper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShowAllServlet", value = "/showAll.action")
public class ShowAllServlet extends CommonServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBHelper db = new DBHelper();
        List<Cust> list=db.select("select * from cust", Cust.class);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<ul>");
        for (Cust c:list){
            out.println("<li>"+c+"</li>");
        }
        out.println("</ul>");
        out.flush();
    }
}
