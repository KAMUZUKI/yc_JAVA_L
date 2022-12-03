package com.mu.web.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mu.utils.StringNullAdapter;
import com.mu.web.model.JsonModel;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

//abstract: 即这个类不能当成servlet用，只能继承用
public abstract class CommonServlet extends HttpServlet {

    protected void writeJson(Map map, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setDateFormat("yyy-MM-dd HH:mm:ss");
        gsonBuilder.registerTypeAdapter(String.class,new StringNullAdapter());
        Gson gson = gsonBuilder.create();

        //Gson gson = new Gson();

        String jsonString = gson.toJson(map);
        out.println(jsonString);
        out.flush();
    }

    protected void writeJson(JsonModel jm, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setDateFormat("yyy-MM-dd HH:mm:ss");
        gsonBuilder.registerTypeAdapter(String.class,new StringNullAdapter());
        Gson gson = gsonBuilder.create();
//        Gson gson = new Gson();
        String jsonString = gson.toJson(jm);
        out.println(jsonString);
        out.flush();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("utf-8");

        //TODO:网站后台管理的功能.   像 saas
        //1. 统计客户端系统类型客户端类型
        //2.取客户端的ip，求出客户端的地址
        //3.防止盗链。
        //4.识别用户是否为重复请求。


        super.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Method[] methods = this.getClass().getDeclaredMethods();
        String op = req.getParameter("op");
        for (Method m : methods) {
            if (m.getName().equals(op)) {
                try {
                    m.setAccessible(true); //该方法可以取消Java的权限控制检查，就可以调用类的私有属性和方法
                    m.invoke(this, req, resp);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

//
//    protected void getmethod(HttpServletRequest req, HttpServletResponse resp,Class<?> cls ) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//        Method[] methods = cls.getDeclaredMethods();
//        String op = req.getParameter("op");
//        for (Method m:methods){
//            if(op.equals(m.getName())){
//                m.invoke(cls.newInstance(),req,resp);
//            }
//        }
//
//    }


    protected <T> T parseRequestToT(HttpServletRequest req, Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //
        T obj = cls.newInstance();
        //
        Map<String, String[]> map = req.getParameterMap();

        Method[] ms = cls.getMethods();

        for (Method m : ms) {
            if (m.getName().startsWith("set")) {
                String fieldName = getFieldName(m);
                String[] values = map.get(fieldName);
                String v = null;
                if (values != null) {
                    if (values.length > 1) {
                        StringBuffer sb = new StringBuffer();
                        for (String s : values) {
                            sb.append(s + ",");
                        }
                        v = sb.toString();
                    } else {
                        v = values[0];
                    }
                }
                if (v == null) {
                    continue;
                }
                //判断这个m方法的参数的类型，然后将v 进行类型转换 ，
                String methodParameterTypeName = m.getParameterTypes()[0].getTypeName();
                if ("java.lang.Integer".equals(methodParameterTypeName) || "int".equals(methodParameterTypeName)) {
                    Integer va = Integer.parseInt(v);
                    //然后再调用setXxx( )， 将值注人
                    m.invoke(obj, va);
                } else if ("java.lang.Double".equals(methodParameterTypeName)|| "double".equals(methodParameterTypeName)) {
                    Double va = Double.parseDouble(v);
                    //然后再调用setXxx( )， 将值法人
                    m.invoke(obj, va);
                } else if ("java.lang.Float".equals(methodParameterTypeName)|| "float".equals(methodParameterTypeName)) {
                    Float va = Float.parseFloat(v);
                    //然后再调用setXxx( )， 将值注入
                    m.invoke(obj, va);
                } else if ("java.lang.Long".equals(methodParameterTypeName)|| "long".equals(methodParameterTypeName)) {
                    Long va = Long.parseLong(v);
                    //然后再调用setXxx( ) ， 将值注入
                    m.invoke(obj, va);
                } else {
                    m.invoke(obj, v);
                }//判断这个m方法的参数的类型，然后将v 进行类型转换 ，


            }


        }

        return obj;
    }

    private String getFieldName(Method m) {
        String fieldName = m.getName().substring("set".length());
        fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
        return fieldName;
    }

}
