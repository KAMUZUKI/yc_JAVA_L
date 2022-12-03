package com.mu.web.filter;

import com.mu.utils.Constants;
import com.mu.web.model.JsonModel;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value={"/custOp.action"})
public class RightFilter extends CommonFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req2=(HttpServletRequest)req;
        HttpServletResponse resp2=(HttpServletResponse)resp;

        HttpSession session = req2.getSession();
        String op = req2.getParameter("op");
        if(session.getAttribute(Constants.USER)!=null){
            chain.doFilter(req, resp);
        }else if("findFoodsByPage".equals(op)||"findAllFoods".equals("op")){
            chain.doFilter(req, resp);
        }else{
            JsonModel jm=new JsonModel();
            jm.setCode(-1);
            jm.setMsg("用户尚未登录");
            super.writeJson(jm,resp2);

        }
//        if(session.getAttribute(Constans.RESUSER)==null){
//            JsonModel jm=new JsonModel();
//            jm.setCode(-1);
//            jm.setMsg("用户尚未登录");
//            super.writeJson(jm,resp2);
//        }else{
//            chain.doFilter(req, resp);
//        }


    }



}
