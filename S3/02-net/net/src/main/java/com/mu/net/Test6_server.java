package com.mu.net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 11:24
 **/

public class Test6_server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(20000);
        System.out.println("服务器启动,监听:" + ss.getLocalPort() + "端口");

        boolean flag = true;
        while (flag){
            Socket s = ss.accept();
            System.out.println("客户端" + s.getRemoteSocketAddress() + "连接上了服务器");
            try(Scanner iis = new Scanner(s.getInputStream());
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                Scanner keyboard = new Scanner(System.in);
                ){
                do{
                    String request = iis.nextLine();
                    System.out.println(" if (\"bye\".equalsIgnoreCase(request)){\n" +
                            "                        System.out.println(\"客户端\" + s.getRemoteSocketAddress() + \"断开与客户端的连接...\");\n" +
                            "                        break;\n" +
                            "                    }\n" +
                            "                    System.out.println(\"请输入要发送的内容：\");\n" +
                            "                    String line = keyboard.nextLine();\n" +
                            "                    pw.println(line);\n" +
                            "                    pw.flush();\n" +
                            "                    if (\"bye\".equalsIgnoreCase(line)){\n" +
                            "                        System.out.println(\"客户端\" + s.getRemoteSocketAddress() + \"断开与客户端的连接...\");\n" +
                            "                        break;\n" +
                            "                    }客户端：" + s.getRemoteSocketAddress() + "对server说:" + request);

                }while(flag);
                System.out.println("与客户端" + s.getRemoteSocketAddress() + "的连接中断");
            }
        }
        System.out.println("服务程序结束...");
    }
}
