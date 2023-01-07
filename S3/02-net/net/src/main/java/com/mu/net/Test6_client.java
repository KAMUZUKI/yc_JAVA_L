package com.mu.net;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 11:19
 **/

public class Test6_client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",20000);
        System.out.println("客户端连接服务器" + s.getRemoteSocketAddress() + "的端口" + s.getPort());
        boolean flag = true;
        try(Scanner iis = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            Scanner keyboard = new Scanner(System.in);
        ){
            do{
                System.out.println("请输入要发送的内容：");
                String line = keyboard.nextLine();
                pw.println(line);
                pw.flush();
                if ("bye".equalsIgnoreCase(line)){
                    System.out.println("客户端断开与服务器的连接...");
                    break;
                }
                String response = iis.nextLine();
                System.out.println("服务器回复：" + response);
                if ("bye".equalsIgnoreCase(response)){
                    System.out.println("客户端断开与服务器的连接...");
                    break;
                }
            }while (flag);
            System.out.println("连接中断");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("客户端退出...");
    }
}
