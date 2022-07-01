package com.mu02.utils;

import org.testng.annotations.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class Utils {
    public static String transferSize(long size) {
        if (size / 1024 / 1024 / 1024 / 1024 > 0) {
            return size / 1024 / 1024 / 1024 / 1024 + "T";
        } else if (size / 1024 / 1024 / 1024 > 0) {
            return size / 1024 / 1024 / 1024 + "G";
        } else if (size / 1024 / 1024 > 0) {
            return size / 1024 / 1024 + "M";
        } else if (size / 1024 > 0) {
            return size / 1024 + "k";
        } else {
            return size + "B";
        }
    }

    @Test
    public static void makeName(){
        String allXins = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎";
        int cha = 0x9FA5 - 0x4E00;//0x代表十六进制，中文的数据范围
        String line;
        //PrintWriter按行（自动加换行符） true:追加模式
        try (PrintWriter oos = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File("src\\test\\java\\com\\mu2\\a.txt"), true)))) {
            Random r = new Random();
            for (int i = 0; i < 10000; i++) {
                String id = i + 100 + "";
                int firstIndex = r.nextInt(allXins.length());
                char xin = allXins.charAt(firstIndex);
                int second = r.nextInt(cha) + 0x4E00;
                String name = xin + "" + (char) second;
                line = id + "\t" + name + "\t" + (r.nextInt(10) + 17);
                oos.println(line);//将数据按行写到a.t×t中
                if (i % 100 == 0) {
                    oos.flush();     //每100条数据刷新一次
                }
            }
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("保存成功");

    }

    public static int sum(int[] result){
        int total = 0;
        if (result==null || result.length<=0){
            return 0;
        }
        for (int i=0; i<result.length; i++){
            total+=result[i];
        }
        return total;
    }


    public static void showSystemInfo() throws SQLException {
        System.out.println("=================信息系统如下======================");
        Runtime r = Runtime.getRuntime();
        int cpu = r.availableProcessors();
        long memory = r.freeMemory();
        long totalMemory = r.totalMemory();

        String memoryString = Utils.transferSize(memory);
        String totalMemoryString = Utils.transferSize(totalMemory);
        System.out.println( "memoryString:  " + memoryString + "  totalMemoryString： " + totalMemoryString);

        System.out.println("系统的环境变量");
        Properties p = System.getProperties();
        Set<Map.Entry<Object, Object>> set = p.entrySet();
        for (Map.Entry<Object, Object> entry : set){
            String name = (String)entry.getKey();
            String value = (String) entry.getValue();
            System.out.println(name+"\t"+value);
        }

        System.out.println("=================数据库信息如下=====================");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");
        DatabaseMetaData db = conn.getMetaData();
        String dbname = db.getDatabaseProductName();
        int version = db.getDatabaseMajorVersion();
        System.out.println(dbname+"   "+version);
    }
}


