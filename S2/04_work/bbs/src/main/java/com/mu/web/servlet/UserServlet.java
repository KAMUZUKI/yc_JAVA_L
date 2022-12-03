package com.mu.web.servlet;

import com.mu.bean.User;
import com.mu.dao.DbHelper;
import com.mu.utils.Constants;
import com.mu.utils.Md5;
import com.mu.web.model.JsonModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet" ,value="/tbl_user.action")
public class UserServlet extends CommonServlet {
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        HttpSession session=req.getSession();
        if(session.getAttribute(Constants.USER)!=null){
            jm.setCode(1);
            User tbl_user=(User)session.getAttribute(Constants.USER);
            tbl_user.setUpass("别看");
            jm.setData(tbl_user);
        }else{
            jm.setCode(-1);
        }
        writeJson(jm,resp);
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        HttpSession session=req.getSession();
        session.removeAttribute(Constants.USER);
        jm.setCode(1);
        writeJson(jm,resp);
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonModel jm=new JsonModel();
        User tbl_user=null;
        try {
            tbl_user=super.parseRequestToT(req, User.class);
            String valcode=req.getParameter("valcode");
            HttpSession session=req.getSession();
            boolean validate=false;
            if(validate){
                String code=session.getAttribute("code").toString();
                if(!code.equals((valcode))){
                    jm.setCode(0);
                    jm.setMsg("验证码错误");
                    writeJson(jm,resp);
                    return;
                }
            }
            tbl_user.setUpass(Md5.MD5Encode(tbl_user.getUpass()));
            String sql="select * from tbl_user where uname=? and upass=?";
            DbHelper db=new DbHelper();
            List<User> list=db.select(sql, User.class,tbl_user.getUname(),tbl_user.getUpass());
            if(list!=null&&list.size()>0){
                User rs=list.get(0);
                rs.setUpass("不知道");
                jm.setCode(1);
                jm.setData(rs);
                session.setAttribute(Constants.USER,rs);
            }else{
                jm.setCode(0);
                jm.setMsg("用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        writeJson(jm,resp);
    }

    protected void reg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        try {
            User tbl_user=super.parseRequestToT(req, User.class);
            tbl_user.setUpass(Md5.MD5Encode(tbl_user.getUpass() ));
            String sql="insert into tbl_user(uname,upass,head,regtime,gender) values(?,?,?,now(),?);";
            DbHelper db=new DbHelper();
            int i = db.doUpdata(sql,tbl_user.getUname(), tbl_user.getUpass(),tbl_user.getHead(),tbl_user.getGender());
            if(i>0){
                jm.setCode(1);
            }else{
                jm.setMsg("注册失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg("注册失败：原因："+e.getMessage());
        }
        writeJson(jm,resp);
    }

    //注册前检测用户名是否已经被使用
    protected void checkUname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        try {
            User tbl_user=super.parseRequestToT(req, User.class);
            String sql="select * from tbl_user where uname=?";
            DbHelper db=new DbHelper();
            List<User> list = db.select(sql, User.class, tbl_user.getUname());
            if(list==null||list.size()<=0){
                jm.setCode(1);
            }else{
                jm.setCode(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(-1);
            jm.setMsg("检测用户名失败：原因："+e.getMessage());
        }
        writeJson(jm,resp);
    }
}
