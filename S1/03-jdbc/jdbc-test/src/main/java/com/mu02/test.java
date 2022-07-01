package com.mu02;

import com.mu02.utils.MD5;

public class test {
    public static void main(String[] args) {
        String str = "abc";
        MD5 md5 = new MD5();
        String res = md5.getMd5(str);
        System.out.println(res);

        String str2 = "bca";
        MD5 md52 = new MD5();
        String res2 = md52.getMd5(str2);
        System.out.println(res2);

        String str3 = "cba";
        MD5 md53 = new MD5();
        String res3 = md53.getMd5(str3);
        System.out.println(res3);

        String str4 = "aaa";
        String res4 = MD5.getInstance().getMD5(str4);
        System.out.println(res4);

    }

}
