package com.mu.vote.servlet;

import com.google.gson.Gson;
import com.mu.vote.domain.Result;
import com.mu.vote.domain.TpRecord;
import com.mu.vote.domain.TpUser;
import com.mu.vote.mapper.TpRecordMapper;
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
 * @date : 2022-12-27 20:32
 **/

@WebServlet(name = "VoteServlet",urlPatterns = "/VoteServlet")
public class VoteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("enter VoteServlet");
        String vid = req.getParameter("vid");
        String[] iid = req.getParameterValues("iid");
        final TpUser loginedUser = (TpUser) req.getSession().getAttribute("loginedUser");
        if(loginedUser==null){
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().printf(new Gson().toJson(new Result(0,"请先登录",null)));
            return;
        }
        int usid = loginedUser.getId();

        Result res;
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        try {
            TpRecordMapper tpRecordMapper = sqlSession.getMapper(TpRecordMapper.class);
            for (String i : iid) {
                TpRecord tpRecord = new TpRecord();
                tpRecord.setVid(Integer.parseInt(vid));
                tpRecord.setIid(Integer.valueOf(i));
                tpRecord.setUsid(Integer.valueOf(usid));
                tpRecordMapper.insert(tpRecord);
            }
            sqlSession.commit();
            res = new Result(1, "投票成功", null);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            res = new Result(0, "投票失败", null);
        }finally {
            sqlSession.close();
        }
        String json = new Gson().toJson(res);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().printf(json);
    }
}
