package src.com.mu.project19_io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestFile1 {
    public static void main(String[] args) {
        // 路径分隔符：windows下  \   linux:   /
        System.out.println(File.separator);
        System.out.println(File.separatorChar);

        // 用在环境变量中用于分隔两个变量地址的分隔符
        // windows系统 ;  linux :
        System.out.println(File.pathSeparator);
        System.out.println(File.pathSeparatorChar);

        // 常用路径:
        System.out.println("用户路径，当前用户有完全操作权限：" + System.getProperty("user.home"));  // C:\Users\MUZUKI
        System.out.println("项目路径：" + System.getProperty("user.dir"));  // D:\Java_L\01_API

        // ==============================
        // 需求：在 用户路径判断是否有某个目录 a ，如果没有，则创建，有则输出 纯在这个目录
        // File f = new File("c:\\User\\MUZUKI");  //  \为转义符，使用\\表示这是一个路径分隔符
        String userhome = System.getProperty("user.home");
        // File f = new File(userhome,"a");  // 写法一
        File f = new File(userhome + File.separator + "a");  // 写法二
        if (f.exists() == false){
            f.mkdirs();
            System.out.println(f.getPath() + ",目录创建成功");
        }else {
            System.out.println(f.getPath() + ",目录已存在");
        }

        // ==============================
        // 需求二： 每天/秒创建一个目录，目录的格式  yyyy/MM/dd/ss
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    createFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },1000,1000);
    }

    private static void createFile() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator + "ss");
        String str = localDateTime.format(formatter);
        System.out.println(str);

        File f = new File(System.getProperty("user.home"),str);
        if (f.exists() == false){
            f.mkdirs();
            System.out.println(f.getPath() + "创建成功");
        }
        File f1 = new File(f.getPath(),"test.txt");

    }
}
