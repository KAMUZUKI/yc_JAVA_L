package com.mu.web.filter;

import com.mu.web.model.JsonModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author : MUZUKI
 * @date : 2022-10-15 19:40
 * @description : 权限过滤器
 **/

@WebFilter(value={"/custOp.action","/resorder.action"})
public class RightFilter extends CommonFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute("resuser") == null) {
            JsonModel jm = new JsonModel();
            jm.setCode(-1);
            jm.setMsg("用户尚未登录");
            super.writeJson(jm,response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("权限过滤器销毁");
    }
}
