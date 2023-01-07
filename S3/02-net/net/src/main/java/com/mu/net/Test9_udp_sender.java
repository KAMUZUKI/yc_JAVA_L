package com.mu.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 12:01
 **/

public class Test9_udp_sender {
    public static void main(String[] args) throws IOException {
        String s = "hello world";
        DatagramPacket dp = new DatagramPacket(s.getBytes(),s.getBytes().length,new InetSocketAddress("localhost",20000));
        DatagramSocket ds = new DatagramSocket(1890);
        ds.send(dp);
        ds.close();
        System.out.println("发送udp数据包完毕");
    }
}
