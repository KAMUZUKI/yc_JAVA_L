package com.mu.web.Listener;

import redis.clients.jedis.Jedis;
import com.mu.dao.RedisHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class SystemInfoListener implements ServletContextListener {
    Timer t;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("应用程序启动。。。，创建 application了 ");
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        System.out.println("应用程序启动,时间:"+ new Date() );
        System.out.println("操作系统类型: "+osName);
        System.out.println("操作系统版本:"+ osVersion );
        System.out.println("空闲内存:"+ Runtime.getRuntime().freeMemory() );

        t=new Timer(true );
        Calendar c=Calendar.getInstance();
        c.add( Calendar.DATE,1);
        c.add(Calendar.HOUR_OF_DAY,0);
        c.add(Calendar.MINUTE,0);
        c.add(Calendar.SECOND,0);
        t.schedule(new MyTimerTask(),c.getTime(),24*60*60*1000);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       if(t!=null){
           t.cancel();
       }


    }
}

class  MyTimerTask extends TimerTask {
    @Override
    public void run() {
        Calendar c=Calendar.getInstance();
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        Jedis jedis= RedisHelper.getRedisInstance();
        jedis.del( weekDay+"");
        jedis.close();
    }
}