package src.com.mu.project17_lang;

import java.util.Scanner;

public class Test9_String2 {
    // indexOf  lastIndexOf substring
    public static void main(String[] args) {
        // 查找
        String s1 = "http://www.sina.com";
        int index1 = s1.indexOf(':');  // char的数据  0-65535,正好可以放在 int
        System.out.println("index1 = " + index1);
        int index2 = s1.indexOf('.');
        System.out.println("index2 = " + index2);

        int index3 = s1.indexOf(104);  //  indexOf()中的参数是  int 但，可以是一个字符，也可以是一个数字
        System.out.println("index3 = " + index3);

        // 查子串
        int index4 = s1.indexOf("www");
        System.out.println("index4 = " + index4);

        // 从后向前查
        int index5 = s1.lastIndexOf('.');
        System.out.println("index5 = " + index5);
        int index6 = s1.lastIndexOf(".");
        System.out.println("index6 = " + index6);

        //指定位置向后查
        int index7 = s1.indexOf('.',11);
        System.out.println(index7);

        //案例：输入一个邮箱，请输出   欢迎xxx，并输出邮箱的类型
        Scanner sc = new Scanner(System.in);
        System.out.println("输入一个邮箱地址");
        String email  = sc.nextLine();
        int atIndex = email.indexOf("@");
        String name = email.substring(0,atIndex);
        System.out.println("欢迎您," + name);

        int lastdotindex = email.lastIndexOf(".");
        String type = email.substring(lastdotindex+1);
        if ("com".equalsIgnoreCase(type)){
            System.out.println(email + "是商业网站  邮箱");
        } else if ("org".equalsIgnoreCase("type")){
            System.out.println(email + "是一个组织机构 的邮箱");
        }
    }
}
