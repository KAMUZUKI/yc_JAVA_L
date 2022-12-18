package com.mu;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : MUZUKI
 * @date : 2022-12-11 15:29
 **/

public class AtomicTest {
    // volatile 不保证原子性
    // 原子类的 Integer
    private static AtomicInteger num = new AtomicInteger();

    public static void add() {
        // num++; // 不是一个原子性操作
        num.getAndIncrement(); // AtomicInteger + 1 方法， CAS
    }

    @Test
    public void test() {
        long start = System.nanoTime();
        //理论上num结果应该为 2 万
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (long j = 0; j < 10_0000L; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) { // main gc
            Thread.yield();
        }
        long end = System.nanoTime();
        long nanoTime = end - start;
        System.out.println(Thread.currentThread().getName() + " " + num + " it spend " + nanoTime);
    }
}
