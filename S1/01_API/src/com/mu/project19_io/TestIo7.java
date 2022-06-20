package src.com.mu.project19_io;

import java.io.*;

public class TestIo7 {
    public static void main(String[] args) {
        // 需求： 复制一个比较大的文件
        // 分析： 要有两种流，一种读数据，另一种写数据
        File source = new File("a.mkv");
        File destination = new File("b.mkv");

        long start = 0;
        try (InputStream is = new BufferedInputStream(new FileInputStream(source));
             OutputStream os = new BufferedOutputStream(new FileOutputStream(destination))
        ) {
            start = System.currentTimeMillis();
            byte[] bytes = new byte[1024 * 1024 * 24];
            int len = -1;
            while ((len = is.read(bytes, 0, bytes.length)) != -1) {
                System.out.println("本次读到：" + len + "字节");
                os.write(bytes, 0, bytes.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("复制完成");
        long end = System.currentTimeMillis();
        System.out.println("消耗时间：" + (end - start) + "ms");
    }
}
