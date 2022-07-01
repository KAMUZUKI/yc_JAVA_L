package src.com.mu.project19_io;

import java.io.File;

public class TestFile2 {
    public static void main(String[] args) {
        File[] fs = File.listRoots();
        if (fs == null || fs.length <= 0){
            return;
        }
        String s = System.getenv("OS");
        System.out.println("当前系统为：" + s);
        for (File f : fs) {
            System.out.println(f.getPath() + "\t" + transferSize(f.getTotalSpace()) + "\t" + transferSize(f.getFreeSpace()));
        }
    }

    private static String transferSize(long size){
        if(size/1024/1024/1024/1024 > 0){
            return size/1024/1024/1024/1024 + "T";
        }else if(size/1024/1024/1024 > 0){
            return size/1024/1024/1024 + "G";
        }else if(size/1024/1024 > 0){
            return size/1024/1024 + "M";
        }else if (size/1024 > 0){
            return size/1024 + "k";
        }else{
            return size + "B";
        }

    }
}
