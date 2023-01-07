package com.mu.net.comcat2.javax.servlet;

import com.mu.net.comcat2.javax.servlet.http.HttpServletRequest;
import com.mu.net.comcat2.javax.servlet.http.HttpServletResponse;

/**
 * @description: 服务端小程序 接口
 * @author : MUZUKI
 * @date : 2023-01-06 19:41
 **/

public interface Servlet {
    /**
     * 初始化方法:  在生命周期中，是在构造方法后调用一次
     */
    public void init();

    /**
     * 销毁方法
     */
    public void destroy();

    /**
     * 每次请求都会调用service
     * @param request
     * @param response
     */
    public void service(ServletRequest request, ServletResponse response);
}
