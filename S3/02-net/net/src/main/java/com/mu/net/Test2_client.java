package com.mu.net;

import java.io.IOException;
import java.net.Socket;

/**
 * @author : MUZUKI
 * @date : 2022-12-18 16:45
 **/

public class Test2_client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("14.215.177.39",80);
        System.out.println("连接成功"+s);
    }

}
