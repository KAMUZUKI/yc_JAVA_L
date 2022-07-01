package src.com.mu.project17_lang;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Test8_String {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s;
        // final  修饰
        // char[] values
        // 实现接口： 序列化 Serializable, 比较 Comparable<String>, 字符序列 CharSequence,
        String s2 = new String("abc");
        String s3 = s2 + "efg";
        System.out.println("字符串不可变：" + s2.hashCode() + "\t" + s3.hashCode());

        //
        char[] chs = new char[]{'h','e','l','l','o'};
        String s4 = new String(chs);
        System.out.println(s4);

        byte[] bs = new byte[]{97,98,99,100,101};
        String s5 = new String(bs);
        System.out.println(s5);

        String s6 =  "中国是一个big国家";
        byte[] bs2 = s6.getBytes(StandardCharsets.UTF_8);
        for (byte b:bs2) {
            System.out.println(b);
        }

        String s7 = new String(bs2,"utf-8");
        System.out.println(s7);

        System.out.println("s7.length()" + s7.length());

        //默认情况下，object中的equals比的是两个对象的引用，只有重写过，才会去比内容
        String s8 = new String("efg");
        String s9 = new String("efg");
        System.out.println("s8.equals(s9) " + s8.equals(s9));

        //补充： equals与 == 之间的区别
        System.out.println("s8==s9  " + (s8==s9));  //false 因为  == 比较地址

        String s10 = "efg";
        String s11 = "efg";  // 常量，放在jvm的常量区中，有优化机制
        System.out.println("s10.equals(s11)  " + s10.equals(s11));
        System.out.println("s10 == s11  " + (s10 == s11));

        System.out.println("s11.equals(s9)  " + s11.equals(s9));
        System.out.println("s11==s9  " + (s11==s9));

        // 比较：equalsIgnoreCase  忽略大小写
        String s12 = new String("hello");
        String s13 = new String("Hello");
        System.out.println("s12.equals(13)  " + s12.equals(13));  //false
        System.out.println("s12.equalsIgnoreCase(s13)  " + s12.equalsIgnoreCase(s13));  //true

        String s14 = new String("HelloWorld");
        String s15 = new String("Hello");
        System.out.println("s14.contains(s15)  " + s14.contains(s15));

        System.out.println("===============");
    }
}
