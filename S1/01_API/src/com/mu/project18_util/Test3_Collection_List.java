package src.com.mu.project18_util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Test3_Collection_List {
    public static void main(String[] args) {
        // 方案一：多态原则：Collection中没有按索引访问的方法，不方便
        Collection c = new ArrayList();
        List list = new ArrayList();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int cha = 0x9F5A;
        }
    }

}
