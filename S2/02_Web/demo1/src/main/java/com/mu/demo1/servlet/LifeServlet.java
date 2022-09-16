package com.mu.demo1.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MUZUKI
 */

@WebServlet(name="life",value="/life")
public class LifeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet()");
        resp.setContentType("text/html");

        // Hello
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "LifeServlet" + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service()");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("destroy()");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init()");
        super.init();
    }
}
