package src.com.mu.project18_util;

import java.io.File;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class Test4 {
    public static void main(String[] args) {
        Properties p = System.getProperties();
        p.forEach((k,v)->{
            System.out.println("keys: " + k + "value: " + v);
        });

        System.out.println("=====================");

        Set<Object> keys = p.keySet();
        Iterator<Object> it = keys.iterator();
        while (it.hasNext()){
            String k = (String)it.next();
            String v = (String)p.get(k);
            System.out.println(k + ":\t" + v);
        }
    }
}
