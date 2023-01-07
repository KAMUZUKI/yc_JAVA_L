package com.mu.Thread;

/**
 * @Classname Test18_producer_consumer
 * @Description 生产者与消费者
 * @Date 2022/12/7 20:49
 * @Created by MUZUKI
 */

public class Test18_producer_consumer {
    public static void main(String[] args) {
        //  -> 程序
        AppleBox ab=new AppleBox();
        Producer p1=new Producer( ab  );
        new Thread( p1) .start();
        Consumer c1=new Consumer(  ab );
        new Thread( c1 ).start();
        Consumer c2=new Consumer(  ab );
        new Thread( c2 ).start();

    }

}

/**
 * 生产者
 */
class Producer implements Runnable{
    AppleBox ab=null;
    Producer( AppleBox ab){
        this.ab=ab;
    }
    @Override
    public void run() {
        //生成消息存到  appleBox
        for( int i=0;i<5;i++){
            Apple a=new Apple( i );
            //生产消息存到 容器中
            ab.deposite(  a );
            System.out.println(Thread.currentThread().getName()+"生产了:"+ a );
            try {
                Thread.sleep(   (int) (Math.random()*1000 ));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 * 生产者
 */
class Consumer implements Runnable{
    AppleBox ab=null;
    Consumer( AppleBox ab){
        this.ab=ab;
    }
    @Override
    public void run() {
        //从appleBox取消息
        for( int i=0;i<5;i++){
            //生产消息存到 容器中
            Apple a=ab.withdraw();
            System.out.println(Thread.currentThread().getName()+"消费了******:"+ a );
            try {
                Thread.sleep(   (int) (Math.random()*1000 ));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 队列其实就是一个集合，存这个消息(Apple)
 * 中间件
 */

class AppleBox {
    int index = 0;

    /**
     * 消息队列，容量大小
     */
    Apple[] apples = new Apple[5];

    /**
     * 存消息
     * 同步  synchronized   是否已满 或者是否为空
     */
    public void deposite(   Apple apple ){

    }
    //取消息
    public Apple withdraw(  ){
        Apple apple=null;

        return apple;
    }
}

//待处理的消息
class Apple {
    int id;
    Apple(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "apple " + id;
    }
}
