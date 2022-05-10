package src.com.mu.project19_io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class TestIo8 {
    public static void main(String[] args) {
        // 需求：数据流，  操作 数据类型
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("test8.tmp"))
        ){
            dos.writeInt(1);
            dos.writeInt(30);
            dos.writeFloat(10.00f);
            dos.writeUTF("zhangsan");
            dos.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
