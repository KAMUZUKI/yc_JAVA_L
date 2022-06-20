package src.com.mu.project17_lang;

import java.io.IOException;
import java.io.InputStream;

public class Test5_runtime {
    public static void main(String[] args) throws IOException {
        Runtime r = Runtime.getRuntime();
        Process p = r.exec("ping www.baidu.com");
        InputStream iis = p.getInputStream();

        byte[] bs = new byte[1024];
        int length = 0;
        while ((length = iis.read(bs, 0, bs.length)) != -1) {
            String s = new String(bs,0,length,"gbk");
            System.out.println(s);
        }
        iis.close();
    }
}
