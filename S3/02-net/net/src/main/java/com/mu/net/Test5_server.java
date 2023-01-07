package com.mu.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * @description : 小说服务器
 * @author : MUZUKI
 * @date : 2022-12-31 15:15
 **/

public class Test5_server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(20000);
        System.out.println("服务器启动成功,监听：" + ss.getLocalPort() + "端口");

        boolean flag = true;
        while (flag){
            try{
                Socket s = ss.accept();
                System.out.println("客户端" + s.getRemoteSocketAddress() + "连接上了服务器");

                byte[] bs = getContent();

                try(OutputStream os = s.getOutputStream();){
                    os.write(bs);
                    os.flush();
                }catch(Exception e){
                    e.printStackTrace();
                }
                s.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        ss.close();
        System.out.println("服务器关闭");
    }

    public static byte[] getContent() {
        //1.目录得位置
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
        //2.取文件，形成一个数组
        File f = new File(path);
        //利用一个过滤接口，只获取文件
        File[] files = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isFile() && file.getName().endsWith(".txt")){
                    return true;
                }
                return false;
            }
        });
        //3.从数组中随机获取一个文件
        Random r = new Random();
        int index = r.nextInt(files.length);
        File filetoread = files[index];
        try(InputStream iis = new FileInputStream(filetoread);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ){
            byte[] bs = new byte[1024];
            int length = -1;
            while((length = iis.read(bs,0,bs.length)) != -1){
                bos.write(bs,0,length);
            }
            return bos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        //4.读取文件 内容 byte[]
        return null;
    }
}
