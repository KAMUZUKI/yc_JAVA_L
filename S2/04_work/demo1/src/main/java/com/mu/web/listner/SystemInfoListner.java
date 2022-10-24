package com.mu.web.listner;


import com.mu.dao.RedisHelper;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author : MUZUKI
 * @date : 2022-10-16 14:54
 **/
@WebListener
public class SystemInfoListner implements ServletContextListener{
    public SystemInfoListner(){
        System.out.println("ServletContextListner的构造方法执行");
    }

    Timer t;
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

        t = new Timer(true);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        t.schedule(new SystemInfoTask(),c.getTime(),1000*60*60*24);
        System.out.println("删除线程启动");

    }

    class SystemInfoTask extends java.util.TimerTask{
        @Override
        public void run() {
            Calendar c = Calendar.getInstance();
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            Jedis jedis = RedisHelper.getRedisInstance();
            jedis.del(weekday+"");
            System.out.println("删除线程执行");
        }
    }
}

