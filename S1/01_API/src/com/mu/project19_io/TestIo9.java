package src.com.mu.project19_io;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class TestIo9 {
    public static void main(String[] args){
        try (DataInputStream dis = new DataInputStream(new FileInputStream("test8.tmp"))
        ){
            System.out.println(dis.readInt() + "\t" + dis.readInt() + "\t" + dis.readFloat() + "\t" + dis.readUTF());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
