package com.mu.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author : MUZUKI
 * @date : 2022-12-18 16:46
 **/

public class Test4_WordClient {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("localhost", 10001);
            System.out.println("连接成功"+s);
        }catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            is = s.getInputStream();
            isr = new InputStreamReader(is);
            char[] b = new char[128];
            int len = 0;
//            while ((len = is.read(b,0,b.length)) != -1) {
//                System.out.println(new String(b, 0, len, "UTF-8"));
//            }
            while((len = isr.read(b)) != -1){
                String str = new String(b, 0, len);
                System.out.println(str);
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

        return;
    }
}
