package com.mu.servlet;

import com.mu.bean.Resfood;
import com.mu.bean.Resuser;
import com.mu.dao.DbHelper;
import com.mu.model.JsonModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 16:49
 **/

@WebServlet(name = "ResfoodServlet",value = "/resfood.action")
public class ResfoodServlet extends CommonServlet {
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
