package com.mu.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharacterFilter",value="*.action")
public class CharacterFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

//        HttpServletRequest req2=(HttpServletRequest)req;
        req.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);
    }

}
