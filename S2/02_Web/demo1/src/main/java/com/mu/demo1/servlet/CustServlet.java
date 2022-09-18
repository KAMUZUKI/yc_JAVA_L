package com.mu.demo1.servlet;

import com.mu.demo1.bean.Cust;
import com.mu.demo1.bean.JsonModel;
import com.mu.demo1.dao.DbHelper;
import com.mu.demo1.utils.Md5;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author : MUZUKI
 * @date : 2022-09-17 19:57
 **/

@WebServlet(name = "CustServlet",value = "/cust.action")
public class CustServlet extends CommonServlet {
    /**
     * 登录
     * cust.action?op=login
     * @param request
     * @param response
     * @throws Exception
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JsonModel jm = new JsonModel();
        try {
            Cust c = super.parseRequestToT(request,Cust.class);
            c.setPwd(Md5.getInstance().getMd5(c.getPwd()));
            String sql = "select * from cust where uname=? and pwd=?";
            DbHelper db = new DbHelper();
            List<Map<String, Object>> result = db.select(sql,c.getUname(),c.getPwd());

            if (result!=null&&!result.isEmpty()){
                jm.setCode(1);
                jm.setData(c);
            }else{
                jm.setCode(0);
                jm.setMsg("登陆失败，用户名或密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        super.writeJson(jm,response);
    }

    /**
     * cust.action?op=reg
     * @param request
     * @param response
     */
    protected void reg(HttpServletRequest request, HttpServletResponse response){
        Cust cust = null;
        JsonModel jm = new JsonModel();
        try {
            cust = super.parseRequestToT(request,Cust.class);
            //
            cust.setPwd(Md5.getInstance().getMd5(cust.getPwd()));
            DbHelper db = new DbHelper();

            int result = db.doUpdata("insert into cust(uname, pwd, sex, favours, comments, hometown) values(?,?,?,?,?,?)",
                    cust.getUname(), cust.getPwd(), cust.getSex(), cust.getFavours(), cust.getComments(), cust.getHometown());

            if (result>0){
                jm.setCode(1);
                jm.setData(cust);
            }else{
                jm.setCode(0);
                jm.setMsg("注册失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
    }

    /**
     * cust.action?op=showAll
     * @param request
     * @param response
     * @throws IOException
     */
    protected void showAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DbHelper db = new DbHelper();
        List<Cust> list = db.select("select * from cust",Cust.class);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setData(list);
        super.writeJson(jm,response);
    }

    /**
     * cust.action?op=showOne
     * @param request
     * @param response
     */
    protected void showOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cust c = null;
        JsonModel jm = new JsonModel();
        try {
            c = super.parseRequestToT(request,Cust.class);
            Long uid = c.getUid();
            //查数据库
            DbHelper db = new DbHelper();
            List<Cust> list = db.select("select * from cust where uid=?",Cust.class,uid + "");
            if (list != null && list.size()>0){
                jm.setCode(1);
                jm.setData(list.get(0));
            }else{
                jm.setCode(1);
                jm.setMsg("查无此id：" + uid + "的数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }

        super.writeJson(jm,response);
    }
}
