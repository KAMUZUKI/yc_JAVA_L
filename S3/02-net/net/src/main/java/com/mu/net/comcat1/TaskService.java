package com.mu.net.comcat1;

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
            HttpServletRequest request = new HttpServletRequest(this.s,this.iis);
            //响应 本地地址+ 资源地址uri，读取文件 拼接http响应 以流的形式返回给客户端
            HttpServletResponse response = new HttpServletResponse(request,this.oos);
            response.send();
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
