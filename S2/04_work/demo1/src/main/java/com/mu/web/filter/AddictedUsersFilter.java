package com.mu.web.filter;

import com.mu.bean.Resuser;
import com.mu.dao.RedisHelper;
import com.mu.utils.MuConstants;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import redis.clients.jedis.Jedis;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author : MUZUKI
 * @date : 2022-10-22 21:14
 * @description : 用于统计用户的上网，统计连续7天登录用户
 **/

@WebFilter(filterName = "AddictedUsersFilter",value = "/resuser.action")
public class AddictedUsersFilter extends CommonFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String op = request.getParameter("op");
        if ("login".equals(op)){
            //说明是login操作
            HttpSession session = request.getSession();
            if (session.getAttribute(MuConstants.RESUSER) != null){
                //登陆成功
                Resuser resuser = (Resuser) session.getAttribute(MuConstants.RESUSER);
                long uid = resuser.getUserid();

                Jedis jedis = RedisHelper.getRedisInstance();
                //取出服务器的时间 周几 作为键来存
                int weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                jedis.setbit(weekday+"",uid,true);
                jedis.close();
            }
        }
    }
}
