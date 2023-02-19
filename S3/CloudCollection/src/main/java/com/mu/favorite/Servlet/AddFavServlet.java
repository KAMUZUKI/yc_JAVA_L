package com.mu.favorite.Servlet;

import com.google.gson.Gson;
import com.mu.favorite.biz.FavBiz;
import com.mu.favorite.biz.FavBizImpl;
import com.mu.favorite.domain.Favorite;
import com.mu.favorite.domain.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : MUZUKI
 * @date : 2022-12-30 16:06
 **/

@WebServlet("/addFav.s")
public class AddFavServlet extends HttpServlet {
    private FavBiz favBiz = new FavBizImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String flabel = req.getParameter("flabel");
        String furl = req.getParameter("furl");
        String ftags = req.getParameter("ftags");
        String fdesc = req.getParameter("fdesc");
        Favorite favorite = new Favorite();
        favorite.setFlabel(flabel);
        favorite.setFurl(furl);
        favorite.setFtags(ftags);
        favorite.setFdesc(fdesc);
        favBiz.addFov(favorite);

        resp.setContentType("text/html;charset=utf-8");
        String json = new Gson().toJson(new Result(1,"链接添加成功",null));
        resp.getWriter().append(json);
    }
}
