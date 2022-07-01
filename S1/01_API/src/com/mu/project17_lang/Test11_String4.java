package src.com.mu.project17_lang;

public class Test11_String4 {
    public static void main(String[] args) {
        // StringBuffer
        // 特点：  1.线程安全  synchronized
        //        2.append() 返回值 是当前的  StringBuffer对象
        StringBuffer sb = new StringBuffer();
        // 构建器模式
        // 链式调用
        sb.append("hello").append(3.14).append("world");
        System.out.println("sb.toString() " + sb.toString());

        // 特点： 1.线程不安全  因为没有加synchronized
        // 2. append()返回值 是当前的  StringBuilder对象
        StringBuilder sb2 = new StringBuilder();
        sb2.append("hello").append("world").append("bye");
        System.out.println("sb2.toString() " + sb2.toString());
        new String().intern();
    }
}

