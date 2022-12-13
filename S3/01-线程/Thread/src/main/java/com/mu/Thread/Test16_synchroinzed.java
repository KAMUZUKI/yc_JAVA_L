package com.mu.Thread;

import java.util.Random;

/**
 * @author MUZUKI
 */



public class Test16_synchroinzed {
    public static void main(String[] args) {
        SellTickOp sto = new SellTickOp(10);

        Thread t1 = new Thread(sto,"张三");
        Thread t2 = new Thread(sto,"李四");

        t1.start();
        t2.start();
    }
}

class SellTickOp implements Runnable{
    private int tickets;

    Random rand = new Random();

    public SellTickOp(int tickets){
        this.tickets = tickets;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + " 在sell第 " + tickets-- + " 张票");
                    try {
                        wait(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //上面的代码 已近完成 数据更新   Thread.sleep(800);不释放锁 ->  另一个被blocked的线程还在等
//                    try {
//                        Thread.sleep(800);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                }else{
                    return;
                }
            }
        }
    }
}