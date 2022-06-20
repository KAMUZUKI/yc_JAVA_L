package src.com.mu.project17_lang;

import java.lang.reflect.AnnotatedType;

public class Test3_math {
    public static void main(String[] args) {
        System.out.println(Math.E);
        System.out.println(Math.PI);

        System.out.println(Math.random());
        System.out.println("生成10以内的整数：" + (int)(Math.random()*10));

        //向上取整
        System.out.println(Math.ceil(4.9));
        //向下取整
        System.out.println(Math.floor(4.9));
        //四舍五入
        System.out.println(Math.round(4.9));
        System.out.println(Math.max(4.5,7.8));

        System.out.println("绝对值：" + Math.abs(-5.6));

        System.out.println("对数：" + Math.log(5));

        System.out.println("三角函数：" + Math.sin(1));

        System.out.println("平方：" + Math.pow(2,3));

        System.out.println("平方根：" + Math.sqrt(4));
    }
}
