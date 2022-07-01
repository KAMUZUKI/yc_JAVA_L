package src.com.mu.project19_io;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestIo5 {
    public static void main(String[] args) throws IOException {
        try(InputStream fis = new FileInputStream("D:\\Java_L\\01_API\\test.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            byte[] bs = new byte[1024];
            int length;
            while ((length = fis.read(bs,0,bs.length)) != -1) {
//                String str = new String(bs);
//                System.out.println(str);
                baos.write(bs,0,length);
            }
            baos.flush();
            byte[] result = baos.toByteArray();
            String s = new String(result);
            System.out.println(s);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
