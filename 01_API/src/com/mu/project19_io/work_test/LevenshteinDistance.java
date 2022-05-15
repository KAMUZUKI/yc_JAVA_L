package src.com.mu.project19_io.work_test;

import java.io.*;
import java.util.*;

import static src.com.mu.project19_io.work_test.LevenshteinDistance.getMinValue;
import static src.com.mu.project19_io.work_test.LevenshteinDistance.minDistance;

/*
    单词最佳匹配算法的主要目标是找出与作为参数的字符串最相似的单词。要实现一个这样的算法，需要做如下准备。
	1. 单词列表：在我们的例子中使用了英国高级疑难词典（UKACD），这是专门为填字游戏社区编纂的一个单词列表，
	有 250 353 个单词和习惯用语。
	2. 用于评估两个单词之间相似度的指标：
	我们使用 Levenshtein 距离来度量两个字符序列的差异。Levenshtein 距离是指，将第一个字符串转换成第二个字符串所需进行的最少的插入、删
	除或替换操作次数。你可以查看维基百科关于“Levenshtein distance”的解释，找到对这一指标的简要描述。
	在我们的例子中，你可以实现如下两个操作。
	1.  第一个操作使用 Levenshtein 距离返回与某个字符序列最相似的单词列表。
 */

public class LevenshteinDistance {
    public static void main(String[] args) throws Exception {
        System.out.println("输入匹配单词：");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if (input == ""){
            throw new Exception("至少输入一个字符");
        }
        long start = System.currentTimeMillis();
        /*
        FileReader fr = new FileReader("Dictionary.txt");
        BufferedReader br = new BufferedReader(fr);
        Map<Integer,String> map = new Hashtable<>();
        int num = 0;
        String str;
        while ((str = br.readLine()) != null) {
            map.put(++num,str);
        }

        Map<Integer,Integer> result = new HashMap<>();
        int count = map.size();
        for (Map.Entry<Integer,String> entry : map.entrySet()){
            result.put(count--,minDistance(input,entry.getValue()));
        }
        int tmp = (int)getMinValue(result);
        for (Map.Entry<Integer,Integer> entry : result.entrySet()){
            if(entry.getValue().equals(tmp)){
                System.out.println(map.get(entry.getKey()));
            }
        }
        */
        WorldLoader worldLoader =WorldLoader.getInstance();
        worldLoader.getWords(input);
        long end = System.currentTimeMillis();
        System.out.println("总耗时： " + (end - start) + "ms");
    }

    public static Object getMinValue(Map<Integer, Integer> map){
        Collection<Integer> collection = map.values();
        Object[] obj = collection.toArray();
        Arrays.sort(obj);
        //最小key
        Integer minValue = (Integer) obj[0];
        return minValue;
    }

    public static int minDistance(String word1, String word2){
        int n = word1.length();
        int m = word2.length();

        if(n * m == 0)
            return n + m;

        int[][] d = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++){
            d[i][0] = i;
        }

        for (int j = 0; j < m + 1; j++){
            d[0][j] = j;
        }

        for (int i = 1; i < n + 1; i++){
            for (int j = 1; j < m + 1; j++){
                int left = d[i - 1][j] + 1;
                int down = d[i][j - 1] + 1;
                int left_down = d[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1))
                    left_down += 1;
                d[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return d[n][m];
    }
}

class WorldLoader{
    private static WorldLoader instance = new WorldLoader();

    private List<String> words;

    Map<Integer,String> map = new Hashtable<>();

    private WorldLoader(){
        FileReader fr = null;
        try {
            fr = new FileReader("Dictionary.txt");
            BufferedReader br = new BufferedReader(fr);
            int num = 0;
            String str;
            while ((str = br.readLine()) != null) {
                map.put(++num,str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static WorldLoader getInstance(){
        if(instance == null){
            instance = new WorldLoader();
        }
        return instance;
    }

    public void getWords(String input){
        Map<Integer,Integer> result = new HashMap<>();
        int count = map.size();
        for (Map.Entry<Integer,String> entry : map.entrySet()){
            result.put(count--,minDistance(input,entry.getValue()));
        }
        int tmp = (int)getMinValue(result);
        for (Map.Entry<Integer,Integer> entry : result.entrySet()){
            if(entry.getValue().equals(tmp)){
                System.out.println(map.get(entry.getKey()));
            }
        }
    }
}



