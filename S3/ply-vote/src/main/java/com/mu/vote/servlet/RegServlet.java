package com.mu.vote.servlet;

import com.google.gson.Gson;
import com.mu.vote.domain.Result;
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
 * @date : 2022-12-29 17:45
 **/

@WebServlet(name = "RegServlet",value = "/RegServlet")
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        String repwd = req.getParameter("repwd");

        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        Result res;
        try{
            if(uname==null || uname.trim().isEmpty()){
                res = new Result(0,"请输入用户名",null);
            }else if(pwd==null || pwd.trim().isEmpty()) {
                res = new Result(0, "请输入密码", null);
            }else if(repwd==null || repwd.trim().isEmpty()) {
                res = new Result(0, "请再次输入密码", null);
            }else if(!pwd.equals(repwd)) {
                res = new Result(0, "两次输入的密码不一致", null);
            }else{
                TpUserMapper tpRecordMapper = sqlSession.getMapper(TpUserMapper.class);
                tpRecordMapper.insert(uname,pwd);
                sqlSession.commit();
                res = new Result(1,"注册成功",null);
            }
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            res = new Result(0,"注册失败",null);
        }finally {
            sqlSession.close();
        }
        String json = new Gson().toJson(res);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().printf(json);
    }
}
