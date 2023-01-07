package com.mu.net.comcat2;

import com.mu.net.comcat2.javax.servlet.Servlet;
import com.mu.net.comcat2.javax.servlet.ServletContext;
import com.mu.net.comcat2.javax.servlet.ServletRequest;
import com.mu.net.comcat2.javax.servlet.ServletResponse;
import com.mu.net.comcat2.javax.servlet.http.HttpServletRequest;

import java.io.PrintWriter;

/**
 * @author : MUZUKI
 * @description : 动态资源处理接口
 * @date : 2023-01-06 20:25
 **/

public class DynamicProcessor implements Processor {
    /**
     * 处理动态资源
     *
     * @param request
     * @param response
     */
    @Override
    public void process(ServletRequest request, ServletResponse response) {
        //request中的参数已经解析好了

        //1.从request中取出requestURI，到ServletContext的map中去取class
        int contentPathLength = ((HttpServletRequest)request).getContextPath().length();
        String uri = ((HttpServletRequest) request).getRequestURI().replace(((HttpServletRequest)request).getContextPath(),"");
        //2.为了保证单例，先看 另一个map中是否已经有这个class实例，如有，说明是第二次访问，则直接取，再调用service()
        Servlet servlet = null;
        try {
            if (ServletContext.servletInstance.containsKey(uri)) {
                servlet = ServletContext.servletInstance.get(uri);
            } else {
                //3.如果没有，则创建实例，放入map中，再调用service()
                //          若没有则说明servlet是第一次调用
                //                先利用反射创建servlet
                //                再调用init() -> service方法

                Class clazz = ServletContext.servletClass.get(uri);
                //调用servlet的构造方法
                Object obj = clazz.newInstance();
                if (obj instanceof Servlet) {
                    servlet = (Servlet) obj;
                    servlet.init();
                }

                //此servlet 就是客户端访问的servlet
                servlet.service(request, response);
            }
        } catch (Exception e) {
            String bodyEntity = e.toString();
            String protocol = gen500(bodyEntity);
            PrintWriter out = response.getWriter();
            out.println(protocol);
            out.println(bodyEntity);
            out.flush();
            e.printStackTrace();

        }

        //还要考虑些servlet执行失败的情况，输出500错误 响应给客户端
    }

    private String gen500(String bodyEntity) {
        String protocol500 = "HTTP/1.1 500 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + bodyEntity.getBytes() + "\r\n" +
                    "\r\n";
        return protocol500;
    }
}
