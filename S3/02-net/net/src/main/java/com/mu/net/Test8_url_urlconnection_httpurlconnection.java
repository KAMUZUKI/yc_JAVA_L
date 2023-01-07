package com.mu.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 11:54
 **/

public class Test8_url_urlconnection_httpurlconnection {
    public static void main(String[] args) throws IOException {
        // URL
        URL url = new URL("http://www.baidu.com");
        // HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String contentType = connection.getContentType();
        System.out.println("contentType = " + contentType);
        long lengthl = connection.getContentLengthLong();

        try(InputStream is = connection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();){
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = is.read(buffer,0,buffer.length)) != -1){
                System.out.println(new String(buffer,0,length));
            }
            byte[] result = bos.toByteArray();
            String content = new String(result);
            System.out.println("content = " + content);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
