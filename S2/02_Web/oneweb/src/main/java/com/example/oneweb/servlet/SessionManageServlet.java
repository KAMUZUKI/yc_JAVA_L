package com.example.oneweb.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Random;

@WebServlet(name = "SessionManageServlet", value = "/sessionManage.action")
public class SessionManageServlet extends CommonServlet {

    protected void cookieTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("hello", "world");
        Cookie cookie2 = new Cookie("bye", "bye");
        response.addCookie(cookie);
        response.addCookie(cookie2);
        response.sendRedirect("Cookie.jsp");
    }

    protected void requestTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("random")==null){
            Random r= new Random();
            request.setAttribute("random", r.nextInt(100));
        }
        request.setAttribute("name", "abc");
        request.getRequestDispatcher("servlet2.action").forward(request, response);
    }

    protected void sessionTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int num = 1;
        if (session.getAttribute("num")==null){
            session.setAttribute("num", num);
        }else{
            num=Integer.parseInt(session.getAttribute("num").toString());
            session.setAttribute("num", ++num);
        }

        if (num%2==0){
            //session.removeAttribute("random");
            session.invalidate();   //将当前的session设为无效
        }

        //此时再去取session.getAttribute()     illegalStateException
        if (session.getAttribute("random")==null){
            Random r= new Random();
            session.setAttribute("random", r.nextInt(100));
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("sessionid"+ session.getId());

        out.println("<br>random:"+ session.getAttribute("random").toString() );
        out.flush();
    }

    protected void applicationContext(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext application = request.getServletContext();
        int total = 0;
        if(application.getAttribute("total")==null){
            application.setAttribute("total", 1);
        } else {
            total = Integer.parseInt(application.getAttribute("total").toString());
        }
        total++;
        application.setAttribute("total", total);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        String totalString = total+"";
        //循环totalString中的每一个数字
        for(int i=0; i<totalString.length();i++){
            char ch = totalString.charAt(i);
            sb.append("<img src='imgs/"+ch+".png' />");
        }
        out.println("用户访问的总数是:"+sb.toString());
        out.flush();
    }

}
