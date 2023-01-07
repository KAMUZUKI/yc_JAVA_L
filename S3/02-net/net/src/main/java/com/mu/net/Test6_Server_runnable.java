package com.mu.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @description : 多线程的服务端
 * @author : MUZUKI
 * @date : 2023-01-02 11:40
 **/

public class Test6_Server_runnable {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(20000);
        System.out.println("服务器启动成功，监听:" + ss.getLocalPort() + "端口");

        boolean flag = true;
        while (flag) {
            Socket s = ss.accept();
            Thread t = new Thread(new TalkTask(s));
            t.start();
        }
        System.out.println("服务器关闭");
    }
}

class TalkTask implements Runnable{
    private Socket s;

    public TalkTask(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        boolean flag = true;
        System.out.println("客户端:" + s.getInetAddress() + "连接成功");
        try(Scanner iis = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            Scanner keyboard = new Scanner(System.in);
        ){
            do{
                String request = iis.nextLine();
                System.out.println("客户端:" + s.getInetAddress() + "说:" + request);
                if ("bye".equalsIgnoreCase(request)){
                    System.out.println("客户端" + s.getRemoteSocketAddress() + "断开与客户端的连接...");
                    break;
                }
                System.out.println("请输入要发送的内容：");
                String line = keyboard.nextLine();
                pw.println(line);
                pw.flush();
                if ("bye".equalsIgnoreCase(line)){
                    System.out.println("客户端" + s.getRemoteSocketAddress() + "断开与客户端的连接...");
                    break;
                }
            }while (flag);
            System.out.println("与客户端" + s.getRemoteSocketAddress() + "的连接中断");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
