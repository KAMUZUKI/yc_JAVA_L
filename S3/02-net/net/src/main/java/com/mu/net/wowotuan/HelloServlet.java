package com.mu.net.wowotuan;

import com.mu.net.comcat2.javax.servlet.WebServlet;
import com.mu.net.comcat2.javax.servlet.http.HttpServlet;
import com.mu.net.comcat2.javax.servlet.http.HttpServletRequest;
import com.mu.net.comcat2.javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

/**
 * @author : MUZUKI
 * @date : 2023-01-06 20:05
 **/

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    public HelloServlet() {
        System.out.println("HelloServlet构造方法");
    }

    @Override
    public void init() {
        System.out.println("HelloServlet初始化方法");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("HelloServlet   hello world");
        String result = "Hello world你好";
        PrintWriter out = response.getWriter();
        out.println("HTTP/1.1 200 OK\r\nContent-Type: text/html\\r\\nContent-Length: " + result.getBytes().length + "\\r\\n\\r\\n");
        out.flush();
    }
}
