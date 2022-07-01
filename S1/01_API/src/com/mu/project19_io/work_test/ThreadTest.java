package src.com.mu.project19_io.work_test;

import java.io.*;

public class ThreadTest {
    public static void main(String[] args) {
        File f1 = new File("test.mkv"); //测试：创建一个txt文件内容是123456789
        long fileSize = f1.length();
        // 将中间位置取整，对于最中间的一个字节进行两次写入
        long pos = (int)(fileSize/2);

        new Thread(()->{
            try {
                RandomAccessFile ras=new RandomAccessFile(f1, "rw");
                //默认情况下ras的指针为0，即从第1个字节读写到
                ras.seek(0);//将ras的指针设置到0，则读写ras是从第0个字节读写到
                File file2=new File("threadTestWrite2.mkv");
                RandomAccessFile ras2=new RandomAccessFile(file2, "rw");
                ras2.setLength(pos);
                byte[] buffer=new byte[24*1024*1024];
                int len= 0 ;
                long flag = 0;
                while((len=ras.read(buffer))!=-1 && flag <= pos){  // 当指针超过中间位置时退出
                    flag = ras.getFilePointer();  // 记录上一次ras指针位置，否则可能出现不能全部复制的情况，
                    System.out.println(Thread.currentThread().getName() + "写入：" + len + "kb");
                    ras2.write(buffer, 0, len);//从ras2的第0个字节被写入
                }
                ras.close();
                ras2.close();
                System.out.println("ok");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        },"Thread-11111111").start();

        new Thread(()->{
            try {
                RandomAccessFile ras=new RandomAccessFile(f1, "rw");
                ras.seek(pos);//将ras的指针设置到pos，则读写ras是从第pos个字节读写到
                File file2=new File("threadTestWrite2.mkv");
                RandomAccessFile ras2=new RandomAccessFile(file2, "rw");
                ras2.setLength(fileSize - pos);
                ras2.seek(pos); //设置ras2的指针为pos
                byte[] buffer=new byte[24*1024*1024];
                int len=0;
                while((len=ras.read(buffer))!=-1){
                    System.out.println(Thread.currentThread().getName() + "写入：" + len + "kb");
                    ras2.write(buffer, 0, len); //从ras2的第pos个字节被写入，因为前面设置ras2的指针为pos
                    //待写入的位置如果有内容将会被新写入的内容替换
                }
                ras.close();
                ras2.close();
                System.out.println("ok");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        },"Thread-22222222").start();
    }
}
