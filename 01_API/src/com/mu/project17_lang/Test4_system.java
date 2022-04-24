package src.com.mu.project17_lang;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Test4_system {
    public static void main(String[] args) throws IOException {
        //常量
        // in 标准输入流
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println("你输了：" + s);
        // out 标准输出流
        OutputStream oos = System.out;
        oos.write("hello".getBytes());
        oos.flush();
//        oos.close();

        //错误流
        PrintStream ps = System.err;
        ps.println("出错了");
        ps.flush();
        ps.close();

        System.out.println("\n");
        int [] x = {1,2,3,4,5};
        int [] y = new int[x.length];
        System.arraycopy(x,0,y,0,x.length);
        for (int t:y) {
            System.out.println(t + "\t");
        }

        //取时间
        long time1 = System.currentTimeMillis();
        System.out.println("time = " + time1);
        long nano = System.nanoTime();
        System.out.println("nano time = " + nano);

        //系统的环境变量
        Map<String,String> map = System.getenv();
        System.out.println(map);

        System.out.println("\n");
        Properties p = System.getProperties();
        System.out.println(p.getProperty("user.home"));
        System.out.println(p.getProperty("file.encoding"));

    }
}
