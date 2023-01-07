package com.mu.net;

import java.io.*;
import java.net.Socket;

/**
 * @description : 升级流的处理
 * @author : MUZUKI
 * @date : 2022-12-31 15:10
 **/

public class Test5_client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",20000);
        System.out.println("客户端连接服务器" + s.getRemoteSocketAddress() + "的端口" + s.getPort());

        try(InputStream is = s.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ){
          byte[] bs = new byte[1024];
          int length = -1;
            while((length = is.read(bs,0,bs.length)) != -1){
                bos.write(bs,0,length);
            }

            byte[] result = bos.toByteArray();
            String content = new String(result);
            System.out.println(content);
        }catch (Exception e){
            e.printStackTrace();
        }
        s.close();
        System.out.println("客户端断开与服务器的连接...");
    }
}
