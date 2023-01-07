package com.mu.net.comcat2.javax.servlet.http;

import com.mu.net.comcat2.javax.servlet.Servlet;
import com.mu.net.comcat2.javax.servlet.ServletRequest;
import com.mu.net.comcat2.javax.servlet.ServletResponse;

/**
 * @author : MUZUKI
 * @date : 2023-01-06 19:22
 **/

public class HttpServlet implements Servlet {

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doHead(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doTrace(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doOptions(HttpServletRequest request, HttpServletResponse response) {
    }

    /**
     * 模板设计模式：规范httpservlet中的各方法的调用顺序
     *
     * @param request
     * @param response
     */
    @Override
    public void service(ServletRequest request, ServletResponse response) {
        //从request中取出 method(http协议特有)
        String method = ((HttpServletRequest) request).getMethod();
        if ("GET".equalsIgnoreCase(method)) {
            doGet((HttpServletRequest) request, (HttpServletResponse) response);
        } else if ("POST".equalsIgnoreCase(method)) {
            doPost((HttpServletRequest) request, (HttpServletResponse) response);
        } else if ("HEAD".equalsIgnoreCase(method)) {
            doHead((HttpServletRequest) request, (HttpServletResponse) response);
        } else if ("DELETE".equalsIgnoreCase(method)) {
            doDelete((HttpServletRequest) request, (HttpServletResponse) response);
        } else if ("TRACE".equalsIgnoreCase(method)) {
            doTrace((HttpServletRequest) request, (HttpServletResponse) response);
        } else if ("OPTIONS".equalsIgnoreCase(method)) {
            doOptions((HttpServletRequest) request, (HttpServletResponse) response);
        } else {
            //TODO: 错误的响应协议
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) {
        service(request, response);
    }
}
