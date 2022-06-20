package src.com.mu.project19_io;

import java.io.*;
import java.util.Scanner;

public class Test10_reader_writer {
    public static void main(String[] args) {
        // 需求： 录入一段文本存到 文件中，再从文件读出
        // 需求： 使用字符流完成

        // 输出流的用法
        // 需求：提示输入一段文字
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要保存的文本");
        String line = sc.nextLine();

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(new File("a.txt"),true))
        ){
            writer.write(line);
            writer.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("保存成功");

        try (Reader reader = new InputStreamReader(new FileInputStream(new File("a.txt")))
        ){
            char[] chs = new char[4];
            int length = -1;
            while ((length = reader.read(chs,0,chs.length))!=-1){
                String s = new String(chs,0,length);
                System.out.print(s);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
