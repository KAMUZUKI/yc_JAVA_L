package com.example.oneweb.servlet;

import com.example.oneweb.bean.Cust;
import com.example.oneweb.biz.CustBiz;
import com.example.oneweb.servlet.dao.DBHelper;
import com.example.oneweb.model.JsonModel;
import com.example.oneweb.model.PageBean;
import com.example.oneweb.utils.Md5;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CustServlet", value = "/cust.action")
public class CustServlet extends CommonServlet {
    //分页功能
    protected void getPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        try {
            //获取参数:pageno pagesize
            //方案一:request.getParamter();
            //方案二利用父类的parseRequestToT()包装成一个pagebean
            PageBean pageBean = super.parseRequestToT(request, PageBean.class);
            CustBiz cb = new CustBiz();
            pageBean=cb.pageSearch(pageBean);// *
            jm.setCode(1);
            jm.setData(pageBean);
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        super.writeJson(jm, response);
    }


    //登录
    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonModel jm = new JsonModel();
        //前端客户传的
        String loginCode = request.getParameter("loginCode");
        //session中标准的
        HttpSession session = request.getSession();
        String code = session.getAttribute("code").toString();
        if(code.equals(loginCode)==false){
            jm.setCode(0);
            jm.setMsg("验证码错误");
            super.writeJson(jm, response);
        } else {
            try{
                Cust c = super.parseRequestToT(request, Cust.class);
                c.setPwd(Md5.MD5Encode(c.getPwd()));
                String sql = "select * from cust where uname=? and pwd=?";
                DBHelper db = new DBHelper();
                List<Map<String, Object>> result = db.select(sql, c.getUname(), c.getPwd());
                System.out.println(result);
                //方案二:
                if (result!=null&&result.size()>0){
                    jm.setCode(1);
                    jm.setData(c);
                }else{
                    jm.setCode(0);
                    jm.setMsg("登录失败，用户名或密码错误");
                }
            }catch(Exception e){
                e.printStackTrace();
                jm.setCode(0);
                jm.setMsg(e.getMessage());
            }
            super.writeJson(jm, response);
        }
    }

    //注册
    protected void reg (HttpServletRequest request, HttpServletResponse response) throws IOException {
        //方案二
        Cust cust = null;
        JsonModel jm = new JsonModel();
        try{
            cust = super.parseRequestToT(request, Cust.class);
            //加密密码
            cust.setPwd(Md5.MD5Encode(cust.getPwd()));
            DBHelper db = new DBHelper();
            int result = db.doUpdata("insert into cust(uname, pwd, sex, favours, comments, hometown, age, weight) values(?,?,?,?,?,?,?,?)",
                    cust.getUname(), cust.getPwd(), cust.getSex(), cust.getFavours(), cust.getComments(), cust.getHometown(), cust.getAge(), cust.getWeight()) ;
            //方案二：响应的数据格式修改为json 此种方案可以针对多端开发，任意客户端都支持

            if (result > 0){
                jm.setCode(1);
                jm.setData(cust);   //注册成功，将新注册的用户信息回传给客户端
            } else {
                jm.setCode(0);
                jm.setMsg("注册失败");
            }
        } catch(Exception e){
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        super.writeJson(jm, response);
    }

    protected void showAll (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        System.out.println(id);

        DBHelper db = new DBHelper();
        List<Cust> list=db.select("select * from cust", Cust.class);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setData(list);
        super.writeJson(jm, response);
    }

    protected void showOne (HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cust c = null;
        JsonModel jm = new JsonModel();
        try {
            c = super.parseRequestToT(request,Cust.class);
            Long uid = c.getUid();
            //查数据库
            DBHelper db = new DBHelper();
            List<Cust> list = db.select("select * from cust where uid = ?",Cust.class,uid+"");
            if (list!=null && list.size()>0){
                jm.setCode(1);
                jm.setData(list.get(0));
            }else {
                jm.setCode(0);
                jm.setMsg("查无此id:"+uid+"的数据");
            }
        } catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        super.writeJson(jm,response);
    }
}
