package src.com.mu.project19_io;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class test {
    public static void main(String[] args) {
        String s = "abcdefghij";
        char[] c = s.toCharArray();

        new Thread(()->{
            for (int i = 0; i < (int)ceil(c.length/2); i++){
                System.out.println(c[i]);
            }
        },"Thread-1").start();

        new Thread(()->{
            for (int i = (int)floor(c.length/2); i < c.length ; i++){
                System.out.println(c[i]);
            }
        },"Thread-2").start();
    }
}
