package com.example.oneweb.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "LifeServlet",value = "/life")
    //*代表任意路径
//@WebServlet(name="life",value = "*.action")
    //  urlPatterns 同时有多个地址可以访问这个servlet
//@WebServlet(name = "LifeServlet",urlPatterns ={ "/life", "/ll", "/test"})
    //loadOnStartup: -1表示是请求servlet事=时，才创建servlet    0表示，在容器启动时就提前创建servlet
@WebServlet(name = "LifeServlet",urlPatterns ={ "/life", "/ll", "/test"}, loadOnStartup = 0)
public class LifeServlet extends HttpServlet {
    //用构造方法测单例/多例
    public LifeServlet(){
        System.out.println("lifeServlet的构造方法");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init()");
        System.out.println(config);
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用了service");
        super.service(req, resp);   //父类的构造方法中有一些关键的代码（模板代码）
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet()");
        //super.doGet(req, resp); //空的
    }

    @Override
    public void destroy() {
        System.out.println("destroy()");
        super.destroy();
    }


}
