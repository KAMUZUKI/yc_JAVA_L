package com.yc.web.servlet;

import com.yc.bean.User;
import com.yc.dao.DBHelper;
import com.yc.utils.Md5;
import com.yc.web.model.JsonModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/user.action")
public class UserServlet extends CommonServlet {

    //注册:op=reg
    protected void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        DBHelper db=new DBHelper();
        //获取参数,存到一个User对象里面,并存入数据库
        User user = new User();
        try {
            user = super.parseRequestToT(request, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm,response);
            return;
        }
        String sql="insert into user(uname,upass,regtime,head,gender) values(?,?,now(),?,?)";
        int result= 0;
        try {
            result = db.doUpdata(sql,user.getUname(), Md5.getInstance().getMD5(user.getUpass()),user.getHead(),user.getSex().equals("女") ?0:1);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm,response);
            return;
        }
        if(result!=0){
            jm.setCode(0);
            jm.setMsg("注册失败...");
        }
        jm.setCode(1);
        jm.setData(user);
        super.writeJson(jm,response);
    }

    //退出登录:op=logout
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //删除session里面的user
        JsonModel jm = new JsonModel();
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        jm.setCode(1);
        super.writeJson(jm, response);
    }

    //判断是否登录:op=checkLogin
    protected void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建user对象,从session中取出user
        User user = new User();
        JsonModel jm = new JsonModel();
        HttpSession session=request.getSession();
        user=(User)session.getAttribute("user");
        if(user==null){
            jm.setCode(0);
        }else{
            jm.setCode(1);
            jm.setData(user);
        }
        super.writeJson(jm,response);
    }

    //登录:op=login
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建user对象,将参数取出存入user
        User user = new User();
        JsonModel jm = new JsonModel();
        try {
            user = super.parseRequestToT(request, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        if(user.getUname()==null||user.getUpass()==null){
            jm.setCode(0);
            jm.setMsg("请输入用户名与密码....");
            super.writeJson(jm, response);
            return;
        }
        //2.验证码是否正确
        //用户输入的验证码
        String valcode = request.getParameter("valcode");
        //取出标准验证码
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("code");

        if (obj == null) {
            jm.setCode(0);
            jm.setMsg("请输入验证码....");
            super.writeJson(jm, response);
            return;
        }
        String code = obj.toString();
        //验证码判断
        boolean valcodeFlag = false;
        if (valcodeFlag) {
            if (!code.equals(valcode)) {
                jm.setCode(0);
                jm.setMsg("验证码错误");
                super.writeJson(jm, response);
                return;
            }
        }
        //3.访问数据库,看是否有这个用户,验证密码是否正确
        String sql = "select * from user where uname=? and upass=?";
        DBHelper db = new DBHelper();
        List<User> list = db.select(sql, User.class, user.getUname(), Md5.getInstance().getMD5(user.getUpass()));
        if (list != null && list.size() > 0) {
            User user1 = list.get(0);
            user1.setUpass("别偷看");
            jm.setCode(1);
            jm.setData(user1);
            //登录成功,键用户信息记录到session里面
            session.setAttribute("user", user1);
        } else {
            jm.setCode(0);
            jm.setMsg("登陆失败!用户名或密码错误");
        }
        super.writeJson(jm,response);
    }
}

