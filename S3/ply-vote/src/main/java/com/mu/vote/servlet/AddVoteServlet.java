package com.mu.vote.servlet;

import com.google.gson.Gson;
import com.mu.vote.domain.Result;
import com.mu.vote.domain.TpItems;
import com.mu.vote.domain.TpVote;
import com.mu.vote.mapper.TpItemsMapper;
import com.mu.vote.mapper.TpVoteMapper;
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
 * @date : 2022-12-29 17:13
 **/

@WebServlet(name = "AddVoteServlet",urlPatterns = "/AddVoteServlet")
public class AddVoteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        TpVoteMapper tpVoteMapper = sqlSession.getMapper(TpVoteMapper.class);
        TpItemsMapper tpItemsMapper = sqlSession.getMapper(TpItemsMapper.class);

        String vname = req.getParameter("vname");
        String vtype = req.getParameter("vtype");
        String[] iname = req.getParameterValues("inames");

        Result res;
        try{
            TpVote tv = new TpVote();
            tv.setVname(vname);
            tv.setVtype(vtype);
            tpVoteMapper.insert(tv);
            for (String i : iname) {
                TpItems ti = new TpItems();
                ti.setVid(tv.getId());
                ti.setIname(i);
                tpItemsMapper.insert(ti);
            }
            sqlSession.commit();
            res = new Result(1,"投票添加成功", null);
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            res = new Result(0,"投票添加失败", null);
        }finally {
            sqlSession.close();
        }
        String json = new Gson().toJson(res);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().printf(json);
    }
}
