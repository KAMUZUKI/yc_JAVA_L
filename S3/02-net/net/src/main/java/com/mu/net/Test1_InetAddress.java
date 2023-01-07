package com.mu.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author : MUZUKI
 * @date : 2022-12-18 16:43
 **/

public class Test1_InetAddress {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia1 = InetAddress.getLocalHost();
        System.out.println(ia1);

        InetAddress[] ia2 = InetAddress.getAllByName("www.baidu.com");
        if (ia2 != null){
            for (InetAddress ia : ia2) {
                System.out.println(ia);
            }
        }
    }
}
