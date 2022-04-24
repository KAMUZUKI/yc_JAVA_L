package src.com.mu.project17_lang;

public class juc implements Runnable{
    @Override
    public void run() {
        System.out.println("juc class is running");
    }

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is running");
        },"a").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is running");
        },"b").start();
    }
}
