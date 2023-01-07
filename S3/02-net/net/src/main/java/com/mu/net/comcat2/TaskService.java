package com.mu.net.comcat2;

import com.mu.net.comcat2.javax.servlet.ServletContext;
import com.mu.net.comcat2.javax.servlet.http.HttpServletRequest;
import com.mu.net.comcat2.javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author : MUZUKI
 * @date : 2023-01-04 17:49
 **/

public class TaskService implements Runnable {
    private static final Logger logger = Logger.getLogger(TaskService.class);

    private Socket s;
    private InputStream iis;
    private OutputStream oos;
    private boolean flag = true;

    public TaskService(Socket s) {
        this.s = s;
        try{
            this.iis = s.getInputStream();
            this.oos = s.getOutputStream();
        }catch (Exception e){
            logger.error("socket流获取失败...");
            e.printStackTrace();
            flag = false;
        }
    }

    @Override
    public void run() {
        if(this.flag){
            //HttpServletRequest 中解析出所有的请求信息(method, 资源地址url, http版本, 头域(referre,user-agent), 参数parameter)
            //存在 HttpServletRequest 对象中
            //HttpServletRequest request = new HttpServletRequest(this.s,this.iis);
            //响应 本地地址+ 资源地址uri，读取文件 拼接http响应 以流的形式返回给客户端
            //HttpServletResponse response = new HttpServletResponse(request,this.oos);
            //response.send();
            HttpServletRequest request = new HttpServletRequest(this.s,this.iis);
            HttpServletResponse response = new HttpServletResponse(request,this.oos);
            Processor processor = null;
            String testURI = request.getRequestURI().replace(request.getContextPath(),"");
            if (ServletContext.servletClass.containsKey(request.getRequestURI().replace(request.getContextPath(),""))) {
                //这是动态请求
                processor = new DynamicProcessor();
            } else {
                //这是静态请求
                processor = new StaticProcessor();
            }
            processor.process(request,response);
        }
        try{
            this.iis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            this.oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            this.s.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
