package com.mu.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @description : udp 接收端
 * @author : MUZUKI
 * @date : 2023-01-02 11:59
 **/

public class Test10_udp_receiver {
    public static void main(String[] args) throws IOException {
        byte[] bs = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bs,bs.length);
        DatagramSocket ds = new DatagramSocket(20000);
        while(true){
            System.out.println("等待接收数据...");
            ds.receive(dp);
            System.out.println("接收到数据：" + new String(dp.getData(),0,dp.getLength()));
        }
    }
}
