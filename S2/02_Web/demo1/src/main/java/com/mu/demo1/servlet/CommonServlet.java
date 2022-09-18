package com.mu.demo1.servlet;

import com.google.gson.Gson;
import com.mu.demo1.bean.JsonModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * abstract：即这个类不能当作servlet使用，只能继承用
 * @author MUZUKI
 */

public abstract class CommonServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("utf-8");
        super.service(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op=request.getParameter("op");
        try{
            Method m = this.getClass().getDeclaredMethod(op,HttpServletRequest.class,HttpServletResponse.class);
            if(m == null){
                out404("资源地址:"+op,response);
            }else{
                m.invoke(this,request,response);
            }
        }catch(NoSuchMethodException ex){
            ex.printStackTrace();
            out404(ex.getMessage(),response);
        }catch(Exception ex){
            ex.printStackTrace();
            out500(ex.getMessage(),response);
        }
    }

    protected void out500(String message,HttpServletResponse response)throws IOException{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(message);
        out.println("</b>");
        out.flush();
    }

    protected void out404(String message,HttpServletResponse response)throws IOException{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("查无此资源，错误404：<b>");
        out.println(message);
        out.println("</b>");
        out.flush();
    }

    /**
     * 将一个Request转为 T 对象
     */
    protected <T> T parseRequestToT(HttpServletRequest request, Class<T> cls) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        //1.通过class字节码创建一个对象
        T obj = cls.newInstance();
        //2.从request中取出所有的属性，存到这个obj中
        Map<String,String[]> map = request.getParameterMap();
        //3.取出cls中所有的属性，看有几个和map中的键相同，相同则调用cls中的set方法，注入值
        Method[] mt = cls.getMethods();
        for (Method m:mt) {
            if (m.getName().startsWith("set")) {
                //这个m是一个setXxx（），取出Xxx是什么，根据Xxx，在 map.get（Xxx）取出这个值
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
                if (v==null){
                    continue;
                }
                //判断这个m方法的参数类型，然后将v进行类型转换
                String methodParameterTypeName = m.getParameterTypes()[0].getClass().getName();
                if ("java.lang.Integer".equals(methodParameterTypeName)) {
                    Integer va = Integer.parseInt(v);
                    //然后在调用setXxx（），将值注入
                    m.invoke(obj, va);
                } else if ("java.lang.Double".equals(methodParameterTypeName)) {
                    Double va = Double.parseDouble(v);
                    //然后在调用setXxx（），将值注入
                    m.invoke(obj, va);
                } else if ("java.lang.Float".equals(methodParameterTypeName)) {
                    Float va = Float.parseFloat(v);
                    //然后在调用setXxx（），将值注入
                    m.invoke(obj, va);
                } else if ("java.lang.Long".equals(methodParameterTypeName)) {
                    Long va = Long.parseLong(v);
                    //然后在调用setXxx（），将值注入
                    m.invoke(obj, va);
                }else {
                    m.invoke(obj, v);
                }
            }
        }
        return obj;
    }

    private String getFieldName(Method setMethod){
        String fieldName = setMethod.getName().substring("set".length());
        //将fieldName的首字母改小写
        fieldName=fieldName.substring(0,1).toLowerCase()+fieldName.substring(1);
        return fieldName;
    }

    protected void writeJson(JsonModel jm, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String jsonString = gson.toJson(jm);
        out.print(jsonString);
        out.flush();
    }
}
