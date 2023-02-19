package com.mu.vote.servlet;

import com.google.gson.Gson;
import com.mu.vote.domain.Result;
import com.mu.vote.domain.TpUser;
import com.mu.vote.mapper.TpUserMapper;
import com.mu.vote.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : MUZUKI
 * @date : 2022-12-29 17:17
 **/

@WebServlet(name = "LoginServlet",value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("enter LoginServlet");
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        Result res;
        if (name == null || name.trim().isEmpty()) {
            res = new Result(0, "用户名不能为空", null);
        } else if (pwd == null || pwd.trim().isEmpty()) {
            res = new Result(0, "密码不能为空", null);
        } else {
            SqlSession sqlSession = SqlSessionUtils.getSqlSession();
            TpUserMapper tpUserMapper = sqlSession.getMapper(TpUserMapper.class);
            final TpUser tpUser = tpUserMapper.selectForLogin(name, pwd);
            if (tpUser == null) {
                res = new Result(0, "用户名或密码错误", null);
            } else {
                req.getSession().setAttribute("loginedUser", tpUser);
                res = new Result(1, "登录成功", null);
            }
        }
        String json = new Gson().toJson(res);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().printf(json);
    }
}
