package src.com.mu.project19_io;

import java.io.*;

public class Test11_RandomAccess {
    public static void main(String[] args) throws IOException {
        //
        // 需求：事先创建文件并指定大小
        File f1 = new File("file.mp4");
        long fileSize = f1.length();
        File f2 = new File("filetest.mp4");

        int cpus = Runtime.getRuntime().availableProcessors();
        System.out.println("总共有：" + cpus + "个核");
        long sizeperthread = ((fileSize%(long)cpus==0L)?fileSize/cpus:fileSize/cpus +1);
        System.out.println("每个线程要处理的数据量：" + sizeperthread);
        for (int i = 0; i < cpus; i++) {
            Task task = new Task(f1,f2,i,sizeperthread);
            Thread t = new Thread(task);
            t.start();
        }
    }
}

class Task implements Runnable {
    private File f1;
    private File f2;
    private int i;
    private long sizeperthread;

    public Task(File f1,File f2,int i,long sizeperthread){
        super();
        this.f1 = f1;
        this.f2 = f2;
        this.i = i;
        this.sizeperthread = sizeperthread;
    }
    @Override
    public void run() {
        long start = i*sizeperthread;
        RandomAccessFile file1 = null;
        RandomAccessFile file2 = null;
        try {
            file1 = new RandomAccessFile(f1,"rw");
            file2 = new RandomAccessFile(f2,"rw");
            file1.seek(start);
            file2.seek(start);
            byte[] bs = new byte[24*1024*1024];
            int length = -1;
            long flag = 0;
            while ((length = file1.read(bs, 0, bs.length)) != -1 && flag <= (i+1)*sizeperthread){
                flag = file1.getFilePointer();
                String s = Thread.currentThread().getName();
                System.out.println(Thread.currentThread().getName() + "写入：" + length + "b");
                file2.write(bs,0,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file1.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            try {
                file2.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}