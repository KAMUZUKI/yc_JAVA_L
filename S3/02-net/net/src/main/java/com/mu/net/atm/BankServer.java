package com.mu.net.atm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 14:02
 **/

public class BankServer {
    public static void main(String[] args) throws IOException {
        int processCount = Runtime.getRuntime().availableProcessors();
        int corePoolSize = processCount;
        int maximumPoolSize = processCount * 2;
        int keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new NameThreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePloicy();
        ThreadPoolExecutor executor = null;
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, queue, threadFactory, handler);
        executor.prestartAllCoreThreads();

        Bank b = new Bank();
        ServerSocket ss = new ServerSocket(12000);
        System.out.println("银行服务器启动，监听" + ss.getLocalPort() + "端口");
        boolean flag = true;
        while (flag) {
            Socket s = ss.accept();
            System.out.println("ATM客户端" + s.getRemoteSocketAddress() + "连接成功");
//            Thread t = new Thread(new BankTask(s,b));
//            t.start();
            BankTask task = new BankTask(s, b);
            executor.submit(task);
        }
        executor.shutdown();
    }

    /**
     * 线程工厂
     */
    static class NameThreadFactory implements ThreadFactory {
        private final AtomicInteger threadId = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("BankServer-Thread-" + threadId.getAndIncrement());
            return t;
        }
    }

    /**
     * 拒绝策略
     */
    public static class MyIgnorePloicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            System.err.println("线程池：" + e.toString() + r.toString() + "被拒绝");
        }
    }
}
