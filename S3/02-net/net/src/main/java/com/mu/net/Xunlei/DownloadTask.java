package com.mu.net.Xunlei;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 18:17
 **/

public class DownloadTask implements Runnable {
    private int i;
    private long fileSize;
    private int threadSize;
    private long sizePerThread;
    private String url;
    private String newFilePath;
    Xunlei.LengthNotify lentNotify;

    public DownloadTask(int i, long fileSize,int threadSize,long sizePerThread, String url, String newFilePath, Xunlei.LengthNotify lnt) {
        this.i = i;
        this.fileSize = fileSize;
        this.threadSize = threadSize;
        this.sizePerThread = sizePerThread;
        this.url = url;
        this.newFilePath = newFilePath;
        this.lentNotify = lnt;
    }

    @Override
    public void run(){
        long start = i*sizePerThread;
        long end = (i+1)*sizePerThread-1;
        RandomAccessFile raf = null;
        InputStream is = null;

        try{
            raf = new RandomAccessFile(newFilePath,"rw");
            raf.seek(start);

            URL urlobj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Range","bytes="+start+"-"+end);
            conn.setConnectTimeout(10*1000);
            conn.connect();

            is = new BufferedInputStream(conn.getInputStream());
            byte[] bs = new byte[100*1024];
            int length = 0;
            while ((length = is.read(bs,0,bs.length))!=-1){
                raf.write(bs,0,length);
                if (this.lentNotify!=null){
                    this.lentNotify.notifyResult(length);
                }
            }
            System.out.println("线程"+i+"下载完成");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (raf!=null){
                try{
                    raf.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
