package com.yc.web.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yc.utils.StringNullAdapter;
import com.yc.web.model.JsonModel;

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
 * 将一个Request对象转换为 T 对象
 * <p>
 * 这是一个通用的类，一些常用的方法都写在这个里面，可以减少代码冗余
 */
abstract class CommonServlet extends HttpServlet {
    //protected 这个功能它的子类可以用，其他类不能用
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取出op的值
        String op = request.getParameter("op");
        //获取当前Servlet的值
        //Method[] ms=this.getClass().getMethods();
        try {
            Method m = this.getClass().getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            if (m == null) {
                //没有找到这个方法,抛出404错误
                out404("资源地址:" + op, response);
            } else {
                //有这个方法, invoke 这个方法
                m.invoke(this, request, response);
            }
        } catch (NoSuchMethodException noe) {
            out404(noe.getMessage(), response);
        } catch (Exception e) {
            e.printStackTrace();
            out500(e.getMessage(), response);
        }
        //判断op与哪个方法对应
        //再 invoke 这个方法  method.invoke(th is,req,resp)
    }

    protected void out500(String message, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("服务器内部错误,错误编码500:<b>");
        out.println(message);
        out.println("</b>");
        out.flush();
    }

    protected void out404(String message, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("查无此资源,错误编码404:<b>");
        out.println(message);
        out.println("</b>");
        out.flush();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // req.setCharacterEncoding("utf-8");  //将功能移动到 CharaFilter中完成了
        super.service(req, resp);
    }


    protected <T> T parseRequestToT(HttpServletRequest request, Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //1.通过class字节码创建一个对象
        T obj = cls.newInstance();
        //2.从request中取出所有的属性,存到这个 obj 中
        Map<String, String[]> map = request.getParameterMap();
        //3.取出cls中所有属性,看有几个与map中键相同,相同则调用cls中的set方法,注入值
        //这里没有直接取cls中的属性，而是去了方法，是为了不破坏私有属性
        Method[] ms = cls.getMethods();
        for (Method m : ms) {
            if (m.getName().startsWith("set")) {
                //这个m是一个setXXX(),取出XXX是什么,根据XXX,再map.get(XXX)出这个值
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
                //判断这个m方法的参数类型,然后将v进行类型转换
                String methodeParameterTypeName = m.getParameterTypes()[0].getTypeName();
                if ("java.lang.Integer".equals(methodeParameterTypeName) || "int".equals(methodeParameterTypeName)) {
                    Integer va = Integer.parseInt(v);
                    //然后调用setXXX（），将值注入
                    m.invoke(obj, va);
                } else if ("java.lang.Double".equals(methodeParameterTypeName) || "double".equals(methodeParameterTypeName)) {
                    Double va = Double.parseDouble(v);
                    //然后调用setXXX（），将值注入
                    m.invoke(obj, va);
                } else if ("java.lang.Float".equals(methodeParameterTypeName) || "float".equals(methodeParameterTypeName)) {
                    Float va = Float.parseFloat(v);
                    //然后调用setXXX（），将值注入
                    m.invoke(obj, va);
                } else if ("java.lang.Long".equals(methodeParameterTypeName) || "long".equals(methodeParameterTypeName)) {
                    Long va = Long.parseLong(v);
                    //然后调用setXXX（），将值注入
                    m.invoke(obj, va);
                } else {
                    m.invoke(obj, v);
                }
            }
        }
        return obj;
    }

    private String getFieldName(Method setMethod) {
        String fieldName = setMethod.getName().substring("set".length());
        //将这个fieldName的首字母改成小写
        fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
        return fieldName;
    }

    //将需要返回的信息包装成Json格式,并发送
    //后端发送数据给前端，数据格式要统一
    protected void writeJson(JsonModel jm, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");//将返回的信息设置成json格式
        //Gson gson = new Gson();//创建Gson对象,里面有将信息转换为json的方法
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.registerTypeAdapter(String.class,new StringNullAdapter());
        Gson gson=gsonBuilder.create();

        PrintWriter out = response.getWriter();//创建输出对象
        String jsonString = gson.toJson(jm);//将信息转换为json对象
        out.println(jsonString);//输出信息
        out.flush();//释放资源
    }

    protected void writeJson(Map map, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");//将返回的信息设置成json格式
        PrintWriter out = response.getWriter();//创建输出对象
        //Gson gson = new Gson();//创建Gson对象,里面有将信息转换为json的方法

        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.registerTypeAdapter(String.class,new StringNullAdapter());
        Gson gson=gsonBuilder.create();

        String jsonString = gson.toJson(map);//将信息转换为json对象
        out.println(jsonString);//输出信息
        out.flush();//释放资源
    }

}
