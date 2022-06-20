package src.com.mu.project20_test;

import java.io.*;
import java.util.*;


public class TestReadBigTxt {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + File.separator + "movies.dat";
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(f.getPath() + ",数据文件不存在");
            return;
        }
        List<Movie> list = null;
        try (Reader reader = new FileReader(f);
            BufferedReader br = new BufferedReader(reader);) {
            String line = null;
            list = new ArrayList<Movie>();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                Movie movie = parseMovie(line);
                if (movie != null) {
                    list.add(movie);
                }
            }
            br.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("总共有电影条目：" + list.size());

        Map<Integer, Integer> map = getMovieCountbyYear(list);
        System.out.println("输出每年有多少部电影：");
        showMap(map);

        System.out.println("========================================");
        System.out.println("每种类型有多少部电影：");
        Map<String,Integer> map2 = getMovieCountbyType(list);
        showMap(map2);

        System.out.println("分年统计每种类型有多少部电影：");
        Map<Integer,Map<String,Integer>> map3 = getMovieCountbyYearAndType(list);
        showMap(map3);
    }

    public static Movie parseMovie(String line){
        if (line == null){
            return null;
        }
        Movie m = new Movie();
        String[] parts = line.split("::");
        m.setMid(Integer.parseInt(parts[0]));

        String name = parts[1].substring(0,parts[1].lastIndexOf("("));
        m.setName(name.trim());

        String yearstr = parts[1].substring(parts[1].lastIndexOf("(") + 1,parts[1].length() - 1);
        m.setYear(Integer.parseInt(yearstr));

        String[] ss = parts[2].split("\\|");

        List<String> types = Arrays.asList(ss);
        m.setTypes(types);
        return  m;
    }

    private static void showMap(Map map) {
        for (Map.Entry entry : (Set<Map.Entry<Object, Object>>) map.entrySet()){
            Object year = entry.getKey();
            Object type = entry.getValue();
            System.out.println(year + "\t" + type);
        }
    }

    private static Map<String, Integer> getMovieCountbyType(List<Movie> list) {
        Map<String,Integer> map = new HashMap<>();
        for (Movie movie : list){
            List<String> types = movie.getTypes();
            if (types == null || types.size() <= 0){
                continue;
            }
            for (String type : types){
                if(map.containsKey(type)){
                    Integer count = map.get(type);
                    count++;
                    map.put(type,count);
                } else{
                    map.put(type,1);
                }
            }
        }
        return map;
    }

    private static Map<Integer, Map<String, Integer>> getMovieCountbyYearAndType(List<Movie> list) {
        Map<Integer,Map<String, Integer>> map = new TreeMap<>();
        for (Movie m:list){
            Integer year = m.getYear();
            List<String> types = m.getTypes();
            if (map.containsKey(year)){
                Map<String,Integer> typesMap = map.get(year);
                for (String type:types){
                    if (typesMap.containsKey(type)){
                        Integer c = typesMap.get(type);
                        c++;
                        typesMap.put(type,c);
                    } else{
                        typesMap.put(type,1);
                    }
                }
            }else{
                Map<String,Integer> typesMap = new HashMap<>();
                for (String type:types){
                    typesMap.put(type,1);
                }
                map.put(year,typesMap);
            }
        }
        return map;
    }

    private static Map<Integer, Integer> getMovieCountbyYear(List<Movie> list) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Movie movie : list){
            Integer year = movie.getYear();
            if (map.containsKey(year)){
                Integer c = map.get(year);
                c++;
                map.put(year,c);
            }else{
                map.put(year,1);
            }
        }
        return map;
    }

}




