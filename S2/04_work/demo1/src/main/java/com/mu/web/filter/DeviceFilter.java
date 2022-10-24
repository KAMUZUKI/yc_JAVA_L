package com.mu.web.filter;

import com.mu.bean.Resuser;
import com.mu.dao.RedisHelper;
import com.mu.utils.MuConstants;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author : MUZUKI
 * @date : 2022-10-22 19:53
 **/

@WebFilter(filterName = "DeviceFilter",value = "/resuser.action")
public class DeviceFilter extends CommonFilter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String op = request.getParameter("op");
        if ("login".equals(op)){
            //获取浏览器信息
            //如果登陆成功 session中一定有 resuser 一个attribute
            HttpSession session = request.getSession();
            if (session.getAttribute(MuConstants.RESUSER) != null){
                Resuser resuser = (Resuser) session.getAttribute(MuConstants.RESUSER);
                String uid = resuser.getUserid()+"";
                Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
                String device = browser.getName();

                Jedis jedis = RedisHelper.getRedisInstance();
                jedis.sadd(device+MuConstants.REDIS_DEVICE_USERS,uid);
                jedis.lpush(uid+MuConstants.REDIS_USER_DEVICE,device);
                jedis.close();
            }
        }
    }
}
