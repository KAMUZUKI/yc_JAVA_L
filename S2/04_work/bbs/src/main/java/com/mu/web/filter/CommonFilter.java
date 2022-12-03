package com.mu.web.filter;

import com.google.gson.Gson;
import com.mu.web.model.JsonModel;


import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * //   不能加这个@WebFilter(filterName = "CommonFilter")，这是个扩展功能类。
 *          加了这个后会当成一个过滤器使用,就会自动去实例化。
 *          而这个类是个abstract类，不能实例化
 */
public abstract  class CommonFilter implements Filter {
    protected void writeJson(Map map, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String jsonString = gson.toJson(map);
        out.println(jsonString);
        out.flush();
    }

    protected void writeJson(JsonModel jm, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String jsonString = gson.toJson(jm);
        out.println(jsonString);
        out.flush();
    }



}
