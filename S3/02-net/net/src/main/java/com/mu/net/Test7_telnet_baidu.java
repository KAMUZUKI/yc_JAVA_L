package com.mu.net;

import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 11:48
 **/

public class Test7_telnet_baidu {
    public static void main(String[] args) {
        try(Socket s = new Socket("www.baidu.com",80);
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ){
            String http = "GET / HTTP/1.1\r\n\r\n";
            os.write(http.getBytes());
            os.flush();
            byte[] bs = new byte[1024];
            int length = -1;
            while((length = is.read(bs,0,bs.length)) != -1){
                baos.write(bs,0,length);
            }
            byte[] result = baos.toByteArray();
            String content = new String(result);
            System.out.println(content);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
