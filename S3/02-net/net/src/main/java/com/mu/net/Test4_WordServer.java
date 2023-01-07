package com.mu.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : MUZUKI
 * @date : 2022-12-18 16:46
 **/

public class Test4_WordServer {
    public static void main(String[] args) {
        ServerSocket ss = null;
        System.out.println("服务器启动成功");
        for (int i = 10000; i < 65535; i++) {
            try {
                ss = new ServerSocket(10001);
                System.out.println("端口号：" + ss.getLocalPort());
            }catch (Exception e){
                continue;
            }
            break;
        }

        Socket s = null;
        while (true){
            try {
                s = ss.accept();
                System.out.println("连接成功"+s.getRemoteSocketAddress()+"连接上了服务器");
                String filePath = "src\\main\\resources\\static\\" + (int)(Math.random()*3 + 1) + ".txt";
                FileReader fr = new FileReader(filePath);
                BufferedReader br = new BufferedReader(fr);
                OutputStream oos = s.getOutputStream();
                String line;
                while((line = br.readLine()) != null){
                    // 一行一行地处理...
                    oos.write(line.getBytes());
                    System.out.println(line);
                }
                oos.flush();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if (s != null){
                        s.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

