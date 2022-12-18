package com.mu;

import org.junit.Test;

/**
 * @author : MUZUKI
 * @date : 2022-12-11 15:27
 **/

public class VolatileTest {
    // volatile 不保证原子性
    private volatile int num = 0;

    public void add() {
        num++;
    }

    @Test
    public void test() {
        long start = System.nanoTime();
        //理论上num结果应该为 2 万
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) { // main gc
            Thread.yield();
        }
        long end = System.nanoTime();
        long nanoTime = end - start;
        System.out.println(Thread.currentThread().getName() + " " + num  + " it spend " + nanoTime);
    }
}
