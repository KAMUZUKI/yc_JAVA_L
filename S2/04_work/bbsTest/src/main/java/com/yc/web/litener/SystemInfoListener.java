package com.yc.web.litener;

import com.yc.dao.RedisHelper;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class SystemInfoListener implements ServletContextListener {

    Timer t;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println("应用程序 启动..创建 application");
//        //记录系统信息
//        String osName=System.getProperty("os.name");
//        String osversion=System.getProperty("os.version");
//        System.out.println("应用程序启动时间:"+new Date());
//        System.out.println("操作系统类型:"+osName);
//        System.out.println("造作系统版本:"+osversion);
//        System.out.println("空闲内存:"+Runtime.getRuntime().freeMemory());
//
//        //启动一个Timer定时任务
//        t=new Timer(true);
//        Calendar c= Calendar.getInstance();
//        c.add(Calendar.DATE,1);
//        c.set(Calendar.HOUR_OF_DAY,0);
//        c.set(Calendar.MINUTE,0);
//        c.set(Calendar.SECOND,0);
//        t.schedule(new MyTimerTask(),c.getTime(),24*60*60*1000);
//        System.out.println("删除线程启动...");
    }
    @Override   //销毁方法,在服务器关闭时调用
    public void contextDestroyed(ServletContextEvent sce) {
        if(t!=null){
            t.cancel();
        }
    }
}

class MyTimerTask extends TimerTask{
    @Override
    public void run(){
        Calendar c=Calendar.getInstance();
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        Jedis jedis= RedisHelper.getReadisInstance();
        jedis.del(weekday+"");
        jedis.close();
    }
}
