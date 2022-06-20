package src.com.mu.project16_exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test7 {
    public static void main(String[] args) {
        char[] chs = new char[1024];
        try (FileReader fr = new FileReader(new File("D:\\Java_L\\API\\src\\exception\\a.txt"));){
            int number = fr.read(chs);
            String s = new String(chs,0,number);
            System.out.println("读取到的字符串为：" + s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
