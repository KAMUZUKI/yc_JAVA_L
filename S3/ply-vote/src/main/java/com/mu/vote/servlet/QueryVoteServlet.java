package com.mu.vote.servlet;

import com.google.gson.Gson;
import com.mu.vote.domain.TpVote;
import com.mu.vote.mapper.TpVoteMapper;
import com.mu.vote.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-12-27 16:11
 **/

@WebServlet(name = "QueryVoteServlet", value = "/QueryVoteServlet")
public class QueryVoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        TpVoteMapper tpVoteMapper = sqlSession.getMapper(TpVoteMapper.class);
        List<TpVote> tpVotes = tpVoteMapper.selectAll();
        String json = new Gson().toJson(tpVotes);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(json);
    }
}
