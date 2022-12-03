package com.yc.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharacterFilter",value="*.action")
public class CharacterFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //设置编码集
        req.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);//过滤器向后调用
    }


}
