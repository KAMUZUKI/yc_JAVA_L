package src.com.mu.project19_io;

import java.io.File;

public class TestFile4 {
    public static void main(String[] args) {
        // 需求： 给定任一目录，搜索 指定的文件("a","a.txt","*.txt","a*")
        String path = System.getProperty("user.home") + File.separator + "2022";
        File f = new File(path);

        String str = ".txt";
        // 方案二：匿名内部类
//        File[] fs = f.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                if (name.endsWith(str)){
//                    return true;
//                }
//                return false;
//            }
//        });

        // 方案三：函数式，lambda写法

        File[] fs = f.listFiles((dir,name)->{
            if (name.endsWith(str)){
                return true;
            }
            return false;
        });

        if (fs != null && fs.length > 0){
            for (File fi : fs){
                System.out.println(fi.getPath());
            }
        }
    }
}

//方案一：外部类写法
//class Search implements FilenameFilter{
//    private String str;
//
//    public Search(String str){
//        super();
//        this.str = str;
//    }
//
//    @Override
//    public boolean accept(File dir, String name) {
//        if (name.endsWith(str)){
//            return true;
//        }
//        return false;
//    }
//}