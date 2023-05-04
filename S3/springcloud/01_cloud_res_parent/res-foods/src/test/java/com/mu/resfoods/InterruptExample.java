package com.mu.resfoods;

/**
 * @author : MUZUKI
 * @date : 2023-04-17 18:51
 **/

public class InterruptExample implements Runnable {
    @Override
    public void run() {
        while (!Thread.interrupted()) { // 检查中断状态并将其归位
            System.out.println(Thread.interrupted());
            System.out.println("线程正在运行...");
            try {
                Thread.sleep(1000); // 线程休眠1秒
            } catch (InterruptedException e) {
                // 捕捉到InterruptedException异常，表示线程被中断
                System.out.println("线程被中断，但中断状态已被归位");
                // 可以继续处理其他逻辑
            }
        }
        System.out.println("线程退出");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new InterruptExample());
        t.start(); // 启动线程

        try {
            Thread.sleep(3000); // 主线程休眠3秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt(); // 中断线程
    }
}

