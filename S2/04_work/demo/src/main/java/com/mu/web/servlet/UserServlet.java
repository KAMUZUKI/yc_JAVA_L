package com.mu.web.servlet;

import com.mu.bean.Flink;
import com.mu.bean.User;
import com.mu.dao.DbHelper;
import com.mu.utils.Md5;
import com.mu.utils.SendMail;
import com.mu.web.model.JsonModel;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/user.action")
public class UserServlet extends CommonServlet {


//    //user.action?op=alertUserinfo
//    protected void alertUserinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        JsonModel jm = new JsonModel();
//        DbHelper db = new DbHelper();
//        User user=new User();
//        int i=0;
//        String sql="update user set name=?,pwd=?,email=? where id=?";
//        try{
//            user=super.parseRequestToT(request,User.class);
//        }catch ( Exception e){
//            e.printStackTrace();
//            jm.setCode(0);
//            jm.setMsg(e.getMessage());
//            super.writeJson(jm,response);
//            return;
//        }
//        if(i!=1){
//            jm.setCode(0);
//            jm.setMsg("添加失败！");
//            super.writeJson(jm,response);
//            return;
//        }
//        jm.setCode(1);
//        jm.setData(user);
//        super.writeJson(jm,response);
//    }


    //user.action?op=addFlink
    protected void addFlink(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        Flink flink=new Flink();
        int i=0;
        String sql="insert into flink(name,url,img,description,status,sort) values(?,?,?,?,'1',?)";
        try{
            flink=super.parseRequestToT(request,Flink.class);
            i=db.doUpdata(sql,flink.getName(),flink.getUrl(),flink.getImg(),flink.getDescription(),flink.getSort());
        }catch ( Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm,response);
            return;
        }
        if(i!=1){
            jm.setCode(0);
            jm.setMsg("添加失败！");
            super.writeJson(jm,response);
            return;
        }
        jm.setCode(1);
        jm.setData(flink);
        super.writeJson(jm,response);
    }

    //user.action?op=alterPassword
    protected void alterPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        DbHelper db=new DbHelper();
        User user=new User();
        String emailCode = "";
        String email = "";
        int i=0;
        String sql="update user set name=?,pwd=?,email=? where id=?";
        try {
            HttpSession session=request.getSession();
            emailCode=session.getAttribute("emailCode").toString();
            System.out.println(emailCode);
            user=super.parseRequestToT(request,User.class);
            if(user.getEmail()!=null&&emailCode.equals(user.getEmail())){
                i=db.doUpdata(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getId());
            }else {
                jm.setCode(0);
                jm.setMsg("验证码错误！");
                super.writeJson(jm, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        if(i==0){
            jm.setCode(0);
            jm.setMsg("更改失败！");
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(user);
        super.writeJson(jm, response);
    }

    //user.action?op=sendEmail
    protected void sendEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        String emailCode = "";
        String email = "";
        try {
            //生成一个六位数的随机数验证码
            emailCode = (Math.random() + "").substring(2, 8);
            //将这个验证码存到session里面
            HttpSession session = request.getSession();
            session.setAttribute("emailCode", emailCode);
            //取出用户的email参数
            email = request.getParameter("email");
            SendMail sendMail = new SendMail();
            sendMail.sendMail(email, emailCode);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(emailCode);
        super.writeJson(jm, response);
    }

    //user.action?op=register
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        User user = new User();
        String sql = "insert into user(name, account, pwd, phone, email, head, createTime, status, type) values(?, ?, ?, ?, ?, ?, now(), 1, ?)";
        int result = 0;
        try {
            user = super.parseRequestToT(request, User.class);
            user.setPassword(Md5.getInstance().getMD5(user.getPassword()));
            result = db.doUpdata(sql, user.getUsername(), user.getAccount(), user.getPassword(), user.getPhone(), user.getEmail(), user.getHead(), user.getType());
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        if (result == 0) {
            jm.setCode(0);
            jm.setMsg("注册失败...");
        } else {
            jm.setCode(1);
            jm.setData(user);
        }
        super.writeJson(jm, response);

    }


    //user.action?op=login
    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        User user = new User();
        try {
            user = super.parseRequestToT(request, User.class);
            String valcode = request.getParameter("valcode");
            HttpSession session = request.getSession();
            boolean validate = false;
            if (validate) {
                String code = session.getAttribute("code").toString();
                if (!code.equals(valcode)) {
                    jm.setCode(0);
                    jm.setMsg("验证码错误");
                    super.writeJson(jm, response);
                    return;
                }
            }
            user.setPassword(Md5.getInstance().getMD5(user.getPassword()));
            String sql = "select * from user where name=? and pwd=?";
            List<User> list = db.select(sql, User.class, user.getUsername(), user.getPassword());
            if (list != null && list.size() > 0) {
                User rs = list.get(0);
                rs.setPassword("不要偷看");
                jm.setCode(1);
                jm.setData(rs);
                session.setAttribute("user", rs);
                super.writeJson(jm, response);
                return;
            } else {
                jm.setCode(0);
                jm.setMsg("用户名或密码错误");
                super.writeJson(jm, response);
                return;
            }
        } catch (Exception e) {
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
    }
}
