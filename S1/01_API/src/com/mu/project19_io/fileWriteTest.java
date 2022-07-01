package src.com.mu.project19_io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class fileWriteTest {
    public static void main(String[] args) throws IOException {
        File f = new File("test.txt");
        FileWriter fw = new FileWriter(f);
        fw.write("Hello World中国");
        fw.flush();
        fw.close();

        FileReader fr = new FileReader(f);
        char[] buf = new char[13];
        fr.read(buf);
        for (char c : buf) {
            System.out.println(c);
        }
        StringBuilder sb = new StringBuilder();
        StringBuffer sb2 = new StringBuffer();
    }
}
