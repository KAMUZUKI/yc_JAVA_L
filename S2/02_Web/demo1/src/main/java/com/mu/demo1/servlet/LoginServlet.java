package com.mu.demo1.servlet;

import com.mu.demo1.bean.Cust;
import com.mu.demo1.dao.DbHelper;
import com.mu.demo1.utils.Md5;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @author MUZUKI
 */

@WebServlet(name = "LoginServlet", value = "/login.action")
public class LoginServlet extends CommonServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //统一了处理
        //取参数
        request.setCharacterEncoding("utf-8");
        //对请求的参数统一编码
        //        String uname = request.getParameter("uname");
        //        String pwd = request.getParameter("pwd");
        //        if ("张三".equals(uname) && "a".equals(pwd)){
        //            System.out.println("登录成功 success");
        //        }else {
        //            System.out.println("登录失败，用户名或密码错误 fail");
        //        }

      //  pwd = Md5.MD5Encode(pwd,"utf-8");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        try {
            Cust c = (Cust) super.parseRequestToT(request, Cust.class);
            c.setPwd(Md5.getInstance().getMd5(c.getPwd()));
            String sql = "select * from cust where uname=? and pwd=?";
            DbHelper db = new DbHelper();
            List<Map<String, Object>> result = db.select(sql, c.getUname(), c.getPwd());
            if (result !=null && result.size()>0){
                out.println("登录成功，欢迎您："+c.getUname());
            }else{
                out.println("登录失败，用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("出异常了");
        }
        out.flush();

    }
}
