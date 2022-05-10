package src.com.mu.project19_io;

import java.io.*;
import java.util.Scanner;

public class TestIo6 {
    public static void main(String[] args) {
        // 输出流的方法
        // 需求：提示输入一段文本，输出保存到文件中
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入要保存的文本");
        String line = sc.nextLine();

        try (OutputStream os = new FileOutputStream(new File("test6.txt"),true)){
            os.write(line.getBytes());
            os.flush();
            System.out.println("数据保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
