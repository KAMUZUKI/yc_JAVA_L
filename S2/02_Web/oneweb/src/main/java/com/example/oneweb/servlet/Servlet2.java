package com.example.oneweb.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet2", value = "/servlet2.action")
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getAttribute("name").toString();
        System.out.println(value);

        String random = request.getAttribute("random").toString();

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(value);

        out.println("<br>随机数为:"+random);
        out.flush();
    }

}
