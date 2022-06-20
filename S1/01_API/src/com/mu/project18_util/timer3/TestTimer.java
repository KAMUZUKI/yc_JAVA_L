package src.com.mu.project18_util.timer3;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
    // 需求：程序 启动后，延时5秒，然后每一秒输出时间
    public static void main(String[] args) {
        // 定时器
        Timer t = new Timer();
        //写法一：  调用外部类对象
        //            任务           延时  周期
        //t.schedule(new ShowTime(),5000,1000);
        System.out.println("bye,bye");

        // 写法二：使用匿名内部类
        t.schedule(new TimerTask() {    // 抽象不能new，但如果对它进行实现，就可以new
            @Override
            public void run() {
                System.out.println("新时间：" + LocalDateTime.now());
            }
        },5000,1000);
    }
}
