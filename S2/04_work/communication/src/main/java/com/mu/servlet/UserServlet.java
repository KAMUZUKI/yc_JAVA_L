package com.mu.servlet;

import com.mu.bean.Resuser;
import com.mu.dao.DbHelper;
import com.mu.bean.JsonModel;
import com.mu.utils.Md5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 09:19
 **/

@WebServlet(name = "UserServlet",value="/resuser.action")
public class UserServlet extends CommonServlet{
    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    protected void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        HttpSession session = request.getSession();
        if (session.getAttribute("resuser")!=null){
            jm.setCode(1);
            Resuser resuser = (Resuser) session.getAttribute("resuser");
            resuser.setPwd("别偷看...");
            jm.setData(resuser);
        }else{
            jm.setCode(0);
        }
        super.writeJson(jm,response);
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        HttpSession session = request.getSession();
        session.removeAttribute("resuser");
        jm.setCode(1);
        super.writeJson(jm,response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, InstantiationException, IOException {
        JsonModel jm = new JsonModel();
        Resuser resuser = null;

        try {
            logger.info("enter resuser.action login");
            resuser = super.parseRequestToT(request,Resuser.class);
            String valcode = request.getParameter("valcode");
            HttpSession session = request.getSession();
            boolean validate=true;
            if (validate){
                if ("".equals(valcode)){
                    jm.setCode(0);
                    jm.setMsg("验证码不能为空");
                    super.writeJson(jm,response);
                    return;
                }
                String code = session.getAttribute("code").toString();
                if (!code.equals(valcode)){
                    jm.setCode(0);
                    jm.setMsg("验证码错误");
                    super.writeJson(jm,response);
                    return;
                }
            }

            resuser.setPwd(Md5.MD5Encode(resuser.getPwd()));
            String sql = "select * from testuser where username=? and upwd=?";
            DbHelper db = new DbHelper();
            List<Resuser> list = db.select(sql,Resuser.class,resuser.getUsername(),resuser.getPwd());
            if (list!=null&&list.size()>0){
                Resuser rs = list.get(0);
                rs.setPwd("不要偷看");
                jm.setCode(1);
                jm.setData(rs);
                session.setAttribute("resuser",rs);
            }else{
                jm.setCode(0);
                jm.setMsg("用户名或密码错误");
            }
        }catch (Exception e){
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        super.writeJson(jm,response);
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        Resuser resuser = null;

        try {
            logger.info("enter register.action register");
            resuser = super.parseRequestToT(request,Resuser.class);
            String valcode = request.getParameter("valcode");
            HttpSession session = request.getSession();
            boolean validate=true;
            if (validate){
                String code = session.getAttribute("code").toString();
                if (!code.equals(valcode)){
                    jm.setCode(0);
                    jm.setMsg("验证码错误");
                    super.writeJson(jm,response);
                    return;
                }
            }
            resuser.setPwd(Md5.MD5Encode(resuser.getPwd()));
            String sql = "insert into testuser (username,upwd) values (?,?)";
            DbHelper db = new DbHelper();
            int isRegister = db.doUpdata(sql,resuser.getUsername(),resuser.getPwd());
            if (isRegister>0){
                jm.setCode(1);
                jm.setMsg("注册成功");
                session.setAttribute("resuser",resuser);
            }else{
                jm.setCode(0);
                jm.setMsg("注册失败");
            }
        }catch (Exception e){
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        super.writeJson(jm,response);
    }
}
