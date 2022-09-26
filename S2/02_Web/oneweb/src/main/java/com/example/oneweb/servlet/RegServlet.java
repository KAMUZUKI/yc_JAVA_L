package com.example.oneweb.servlet;


import com.example.oneweb.bean.Cust;
import com.example.oneweb.servlet.dao.DBHelper;
import com.example.oneweb.utils.Md5;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "RegServlet", value = "/reg.action")
public class RegServlet extends CommonServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        String headerName="";
        while(headerNames.hasMoreElements()){
            headerName = headerNames.nextElement();
            String value = req.getHeader(headerName);
            System.out.println(headerName+":"+value);
        }
        if (req.getHeader("referer")  ==null || "".equals(req.getHeader("referer"))){
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("此请求是非法注册");
            out.flush();
        }else {
            //应用：识别客户端类型   获取客户端ip（识别客户端区域），识别来源。。。
            super.service(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // request.setCharacterEncoding("utf-8");
//        String uname = request.getParameter("uname");
//        String pwd = request.getParameter("pwd");
//        String sex = request.getParameter("sex");
//        String[] favours = request.getParameterValues("favours");
//        String comments = request.getParameter("comments");
//        String hometown = request.getParameter("hometown");
//
//        pwd = Md5.MD5Encode( pwd, "utf-8");
//        //处理favours的字符串数组
//        StringBuffer sb = new StringBuffer();
//        if (favours!=null&&favours.length>0){
//            for (String s:favours){
//                sb.append(s+",");
//            }
//        }

        //方案二
        Cust cust = null;
        try{
            cust = super.parseRequestToT(request, Cust.class);
        } catch(Exception e){
            e.printStackTrace();
        }

        //加密密码
        cust.setPwd(Md5.MD5Encode(cust.getPwd()));

        //访问数据库，将注册信息添加到数据库中
        DBHelper db = new DBHelper();

//        int result = db.doUpdata("insert into cust(uname, pwd, sex, favours, comments, hometown) values(?,?,?,?,?,?)",
//                uname, pwd, sex, sb.toString(), comments, hometown);

        int result = db.doUpdata("insert into cust(uname, pwd, sex, favours, comments, hometown, age, weight) values(?,?,?,?,?,?,?,?)",
              cust.getUname(), cust.getPwd(), cust.getSex(), cust.getFavours(), cust.getComments(), cust.getHometown(), cust.getAge(), cust.getWeight()) ;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        if (result>0){
            out.println("注册成功，新用户名："+cust.getUname());
        } else{
            out.println("注册失败");
        }
        out.flush();


        //更高级的取参方法
//        Enumeration<String> names = request.getParameterNames();
//        StringBuffer sb = new StringBuffer();
//        while(names.hasMoreElements()){
//            String name = names.nextElement();
//            String[] values = request.getParameterValues(name);
//            if (values.length>1){
//               // System.out.println(name + ":" + Arrays.asList(values));
//                sb.append( name + ":"+ Arrays.asList(values) + "<br>");
//            } else {
//                // System.out.println(name + ":" + values[0]);
//                sb.append( name + ":"+ values[0] + "<br>");
//            }
//        }
//        //操作数据库

       //System.out.println("to reg info:" + uname + "," + pwd + "," + sex + comments + "," + Arrays.asList(favours));

    }
}
