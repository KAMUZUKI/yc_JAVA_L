package src.com.mu.project19_io;

import java.io.File;

public class TestFile3 {
    public static void main(String[] args) {
        // 以递归的方式显示,一个路径下所有的子文件和子目录,并注意排版缩进
        String path = System.getProperty("user.home") + File.separator + "2022";
        showTree(path,0);

        deleteTree(path);
    }

    private static void showTree(String path,int level) {
        // 判断path的类型到底是文件还是目录，如是目录，则递归，文件则直接显示
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < level; i++) {
            sb.append(" ");
        }

        File f = new File(path);
        if (f.isFile()){
            System.out.println(sb.toString() + f.getName());
        }
        if (f.isDirectory()){
            // 取它下面的子文件
            System.out.println(sb.toString() + f.getName());  // 显示目录信息
            File[] fs = f.listFiles();  // 取这个目录下所有的子文件和子目录
            if (fs != null && fs.length > 0){
                for (File fi:fs){
                    showTree(fi.getPath(),level + 1);
                }
            }
        }
    }

    private static void deleteTree(String path){
        File f = new File(path);
        if (f.isFile()){
            f.delete();
            return;
        }
        if(f.isDirectory()){
            File[] fs = f.listFiles();
            if (fs != null && fs.length > 0){
                for (File fi:fs){
                    deleteTree(fi.getPath());
                }
            }
            f.delete();
            System.out.println("删除 " + f.getPath() + "成功");
        }
    }
}
