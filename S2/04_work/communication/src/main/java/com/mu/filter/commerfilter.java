package com.mu.filter;

/**
 * @author : MUZUKI
 * @date : 2022-10-27 19:47
 **/

import com.google.gson.Gson;
import com.mu.bean.JsonModel;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public abstract class commerfilter implements Filter {

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