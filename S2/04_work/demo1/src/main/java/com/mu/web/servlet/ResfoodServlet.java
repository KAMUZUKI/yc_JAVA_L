package com.mu.web.servlet;

import com.mu.bean.Resfood;
import com.mu.biz.ResfoodBiz;
import com.mu.dao.DbHelper;
import com.mu.web.model.JsonModel;
import com.mu.web.model.PageBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 16:49
 **/

@WebServlet(name = "ResfoodServlet",value = "/resfood.action")
public class ResfoodServlet extends CommonServlet {
    protected void findFoodsByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        PageBean pb = null;
        try {
            //1.取前端的分页的参数
            pb = super.parseRequestToT(request,PageBean.class);
            //2.调用业务层完成分页查询
            ResfoodBiz rb = new ResfoodBiz();
            pb=rb.findByPage(pb);

            HttpSession session = request.getSession();
            session.setAttribute("foodList",pb.getDataset());

             jm.setCode(1);
             jm.setData(pb);
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg("查询失败" + e.getMessage());
        }
        super.writeJson(jm,response);
    }

    protected void findAllFoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();

        String sql = "select * from resfood";
        DbHelper db = new DbHelper();
        List<Resfood> list = db.select(sql, Resfood.class);

        jm.setCode(1);
        jm.setData(list);

        super.writeJson(jm,response);
    }
}
