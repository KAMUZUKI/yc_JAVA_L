package com.example.oneweb.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderServlet", value = "/order.action")
public class OrderServlet extends CommonServlet {

    protected void zhifu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("https://www.alipay.com/key=abc&uname=bjc&id="+ request.getParameter("id"));    //重定向
    }

    protected void listCust(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("cust.action?op=showAll").forward(request, response);
    }
}
