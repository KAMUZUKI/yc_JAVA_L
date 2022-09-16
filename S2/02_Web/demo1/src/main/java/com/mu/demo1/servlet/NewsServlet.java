package com.mu.demo1.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author MUZUKI
 */
@WebServlet(name = "news",value = "/news")
public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String lang = req.getParameter("lang");
        String type = req.getParameter("type");

        type = new String(type.getBytes("utf-8"),"gbk");

        String content = "";
        if ("1".equals(id)){
            content="aaaaaaaaaaaaaa";
        }else if ("2".equals(id)){
            content="bbbbbbbbbbbbbb";
        }else{
            content="no such news";
        }
        System.out.println("new type is:" + type);
        System.out.println("result is show with lang:" + lang + " " + content);
    }
}
