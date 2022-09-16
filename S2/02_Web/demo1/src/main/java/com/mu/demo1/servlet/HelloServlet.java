package com.mu.demo1.servlet;

import java.io.*;
import java.util.Enumeration;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author MUZUKI
 */
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    @Override
    public void init() {
        message = "Hello World!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        response.setContentType("text/html");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            out.print(headerName + " : " + request.getHeader(headerName) + "<br />");
        }
    }

    @Override
    public void destroy() {
    }
}