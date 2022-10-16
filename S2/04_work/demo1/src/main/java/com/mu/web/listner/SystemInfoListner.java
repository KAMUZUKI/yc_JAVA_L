package com.mu.web.listner;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

/**
 * @author : MUZUKI
 * @date : 2022-10-16 14:54
 **/
@WebListener
public class SystemInfoListner implements ServletContextListener{
    public SystemInfoListner(){
        System.out.println("ServletContextListner的构造方法执行");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象初始化");
        //记录系统时间
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        System.out.println("应用程序启动时间:" + new Date());
        System.out.println("操作系统名称:" + osName);
        System.out.println("操作系统版本:" + osVersion);
        System.out.println("空闲内存:" + Runtime.getRuntime().freeMemory());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象销毁");
    }
}

