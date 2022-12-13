package com.yc.web.filter;

import com.google.gson.Gson;
import com.yc.web.model.JsonModel;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


public abstract class CommonFilter implements Filter {
    protected void writeJson(JsonModel jm, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");//将返回的信息设置成json格式
        Gson gson = new Gson();//创建Gson对象,里面有将信息转换为json的方法
        PrintWriter out = response.getWriter();//创建输出对象
        String jsonString = gson.toJson(jm);//将信息转换为json对象
        out.println(jsonString);//输出信息
        out.flush();//释放资源
    }

    protected void writeJson(Map map, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");//将返回的信息设置成json格式
        Gson gson = new Gson();//创建Gson对象,里面有将信息转换为json的方法
        PrintWriter out = response.getWriter();//创建输出对象
        String jsonString = gson.toJson(map);//将信息转换为json对象
        out.println(jsonString);//输出信息
        out.flush();//释放资源
    }
}
