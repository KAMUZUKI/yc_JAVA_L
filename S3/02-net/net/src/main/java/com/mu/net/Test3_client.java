package com.mu.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author : MUZUKI
 * @date : 2022-12-18 16:46
 **/

public class Test3_client {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("localhost", 10001);
            System.out.println("连接成功"+s);
            return;
        }catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = s.getInputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = is.read(b,0,b.length)) != -1) {
                System.out.println(new String(b, 0, len, "UTF-8"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
