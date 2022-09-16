package com.mu.demo1.servlet;

import com.mu.demo1.bean.Cust;
import com.mu.demo1.dao.DbHelper;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @author : MUZUKI
 * @date : 2022-09-15 21:22
 **/

@WebServlet(name = "ShowAll",value = "/ShowAll")
public class ShowAllServlet extends CommonServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //访问数据库，将注册信息添加到数据库中
        DbHelper db = new DbHelper();
        Cust cust = null;
        Cust c = null;
        try {
            c = (Cust) super.parseRequestToT(req, Cust.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select * from cust where uname=? and pwd=?";
        List<Map<String, Object>> result = db.select(sql, c.getUname(), c.getPwd());
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
