package com.yc.web.filter;

import com.yc.web.model.JsonModel;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = {"/custOp.action","/resorder.action","/resfood.action"})
public class RightFilter extends CommonFilter {

    public RightFilter(){
        //System.out.println("g走士大夫士大夫");
    }

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //doFilter类似于Servlet中的servce方法
        //配置的请求都会经过这个方法
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        String op=request.getParameter("op");
        HttpSession session=request.getSession();
        if(session.getAttribute("resuser")!=null) {
            //表明是已经登陆的,直接向后
            filterChain.doFilter(servletRequest, servletResponse);
        }else if("findFoodsByPage".equals(op)||"findAllFoods".equals(op)) {
            //ResfoodsServlet中有两个方法不需要登录
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            //是要过滤的地址,但没有登录
            JsonModel jm=new JsonModel();
            jm.setCode(-1);
            jm.setMsg("用户尚未登录");
            //System.out.println(jm.getMsg());
            super.writeJson(jm,response);
        }
    }

    @Override
    public void destroy() {
       // System.out.println("xiaohui");
    }
}
