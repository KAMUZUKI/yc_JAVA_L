package com.mu.demo1.servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : MUZUKI
 * @date : 2022-09-18 15:11
 **/

@WebServlet(name = "SessionManagerServlet",value="/sessionManage.action")
public class SessionManagerServlet extends CommonServlet{
    protected void applicationContext(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext application = request.getServletContext();
        int total = 0;
        if (application.getAttribute("total")==null){
            application.setAttribute("total",1);
        }else{
            total = Integer.parseInt(application.getAttribute("total").toString());
        }
        total++;
        application.setAttribute("total",total);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        String totalString = total + "";
        // 循环取 totalString 中的每一个数字 -> <img src="4.png/>
        for (int i = 0; i < totalString.length();i++){
            char ch = totalString.charAt(i);
            sb.append("<img src='static/"+ ch + ".png'/>");
        }
        System.out.println("sb = " + sb +"; total = " + total);
        out.println("用户访问的总数:" + sb.toString());
        out.flush();
    }
}
