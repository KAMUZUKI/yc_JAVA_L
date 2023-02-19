package com.mu.vote.servlet;

import com.google.gson.Gson;
import com.mu.vote.domain.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : MUZUKI
 * @date : 2022-12-29 17:22
 **/

@WebServlet(name = "GetLoginedUserServlet",value = "/GetLoginedUserServlet")
public class GetLoginedUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Object loginedUser = req.getSession().getAttribute("loginedUser");
        Result res;
        if (loginedUser == null) {
            res = new Result(0, "未登录", null);
        } else {
            res = new Result(1, "已登录", loginedUser);
        }
        String json = new Gson().toJson(res);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().printf(json);
    }
}
