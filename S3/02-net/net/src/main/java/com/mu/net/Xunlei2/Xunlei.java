package com.mu.net.Xunlei2;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 15:53
 **/
public class Xunlei {
    private static int total = 0;

    public static void main(String[] args) {
        String url = "http://dl.baofeng.com/baofeng5/bf5_new.exe";
        long fileSize = getFileSize(url);
        System.out.println("文件大小：" + fileSize);
        String newFileName = getNewFileName(url);
        System.out.println("文件名：" + newFileName);
        String newFilePath = getNewFilePath(newFileName);

        createNewFile(fileSize,newFilePath);

        int threadSize = Runtime.getRuntime().availableProcessors();
        long sizePerThread = getSizePerThread(threadSize,fileSize);
        System.out.println("共" + threadSize + "个线程,每个线程下载的大小：" + sizePerThread);

        List<FutureTask<Integer>> list = new ArrayList<>();
        //循环创建线程
        for (int i = 0; i < threadSize; i++) {
            //这里创建 的是Callable对象，而不是runnable
            DownloadTask downloadTask = new DownloadTask(i,fileSize,threadSize,sizePerThread,url,newFilePath);
            FutureTask<Integer> futureTask = new FutureTask<>(downloadTask);
            list.add(futureTask);
            new Thread(futureTask).start();
        }

        for (FutureTask<Integer> futureTask : list) {
            try {
                total += futureTask.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("下载完成，共下载了" + total + "字节");
    }

    /**
     * 计算每个线程要下载的大小
     * @param threadSize
     * @param fileSize
     * @return
     */
    public static long getSizePerThread(int threadSize, long fileSize){
        return fileSize%threadSize==0?fileSize/threadSize:fileSize/threadSize+1;
    }

    /**
     * 创建空文件 占好位置
     * @param fileSize
     * @param newFilePath
     */
    public static void createNewFile(long fileSize, String newFilePath){
        try(RandomAccessFile raf = new RandomAccessFile(newFilePath,"rw");){
            raf.setLength(fileSize);
            raf.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取文件路径
     * @param newFileName
     * @return
     */
    public static String getNewFilePath(String newFileName){
        String userhome = System.getProperty("user.home");
        return userhome + File.separator + newFileName;
    }

    /**
     * 获取文件名
     * @param url
     * @return
     */
    public static String getNewFileName(String url){
        Date d = new Date();
        DateFormat df = new SimpleDateFormat ("yyyyMMddHHmmss");
        String prefix = df.format(d);
        String suffix = url.substring(url.lastIndexOf("."));
        return prefix + suffix;
    }

    /**
     * 获取文件大小
     * @param url
     * @return
     */
    public static long getFileSize(String url){
        long fileSize = 0;
        try{
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            fileSize = conn.getContentLength();
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileSize;
    }
}

interface Notify{
    public void notifyResult(long length);
}
