package com.mu.net;

import org.junit.Test;

import java.io.*;

/**
 * @author : MUZUKI
 * @date : 2022-12-18 19:56
 **/

public class Test1 {
    @Test
    public void test1() throws IOException {
        String filePath = "src\\main\\resources\\static\\" + (int)(Math.random()*3 + 1) + ".txt";
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            // 一行一行地处理...
            System.out.println(line);
        }
    }
}
