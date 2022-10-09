package com.example.oneweb.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 接收客户端通过url传过来的id号
 *  => HttpServletRequest对象，包装了客户端的请求参数
 */
@WebServlet(name = "news", value = "/news.action")
public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String lang = request.getParameter("lang");
        String type = request.getParameter("type");

        type = new String( type.getBytes("utf-8"), "gbk");

        //查询数据库
        String content = "";
        if("1".equals(id)){
            content = "aaaaaaaa";
        } else if ("2".equals(id)) {
            content = "bbbbbb";
        }else {
            content = "no such news";
        }
        System.out.println("news type is:" + type);
        System.out.println("result is show with lang:" + lang + "" + content);
    }

}
