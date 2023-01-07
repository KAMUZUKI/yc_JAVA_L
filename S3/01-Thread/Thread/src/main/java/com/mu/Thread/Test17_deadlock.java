package com.mu.Thread;

public class Test17_deadlock implements Runnable{
    public int flag = 1;

    static Object o1 = new Object(), o2 = new Object();

    public static void main(String[] args) {
        Test17_deadlock task1 = new Test17_deadlock();
        Test17_deadlock task2 = new Test17_deadlock();

        task1.flag = 1;
        task2.flag = 0;
    }

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("0");
                }
            }
        }
    }

}
