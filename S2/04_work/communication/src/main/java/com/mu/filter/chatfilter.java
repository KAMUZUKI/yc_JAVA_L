package com.mu.filter;

import com.mu.bean.JsonModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author : MUZUKI
 * @date : 2022-10-27 20:17
 **/

@WebFilter(value={"/message.action"})
public class chatfilter extends commerfilter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String op = request.getParameter("op");
        if (session.getAttribute("resuser") != null) {
            //已经登录
            filterChain.doFilter(servletRequest, servletResponse);
        } else if("findFoodByPage".equals(op)||"findAllFoods".equals(op)){
            //未登录，但是是查询操作
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            //未登录，不是查询操作
            JsonModel jm = new JsonModel();
            jm.setCode(-1);
            jm.setMsg("用户尚未登录");
            super.writeJson(jm,response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("权限过滤器销毁");
    }
}
