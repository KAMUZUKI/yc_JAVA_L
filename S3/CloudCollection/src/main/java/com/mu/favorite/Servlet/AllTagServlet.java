package com.mu.favorite.Servlet;

import com.google.gson.Gson;
import com.mu.favorite.domain.Tag;
import com.mu.favorite.mapper.TagMapper;
import com.mu.favorite.util.MybatisHelper;
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
 * @date : 2022-12-30 15:17
 **/

@WebServlet("/allTag.s")
public class AllTagServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession session = MybatisHelper.openSession();
        TagMapper mapper = session.getMapper(TagMapper.class);
        List<Tag> tags = mapper.selectAll();
        Gson gson = new Gson();
        String s =  gson.toJson(tags);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().append(s);
    }
}
