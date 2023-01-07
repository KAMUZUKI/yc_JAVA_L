package com.mu.net;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : MUZUKI
 * @date : 2022-12-18 16:46
 **/

public class Test3_server {
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
                OutputStream oos = s.getOutputStream();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String result = "服务器时间：" + sdf.format(new Date());
                oos.write(result.getBytes());
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
