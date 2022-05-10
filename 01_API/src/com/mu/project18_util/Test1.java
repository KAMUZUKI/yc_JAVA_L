package src.com.mu.project18_util;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Test1 {

    public static void main(String[] args) {
        // 保证在单核JVM上是唯一的
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        // 以后编号用UUID，不用序号
        System.out.println("==================================");

        // 生成随机数
        // 在java.lang.Math类中也有random()方法,在util中是一个类
        Random r = new Random();
        int n1 = r.nextInt();
        System.out.println(n1);
        int n2 = r.nextInt(10);
        System.out.println(n2);
        int n3 = r.nextInt(10) + 10;
        System.out.println(n3);
        System.out.println("==================================");

        // Arrays数组的工具类
        int[] intArrays = { 5, 6, 1, 2, 9, };
        Arrays.sort(intArrays);// 返回的是 intArrays的 hashcode
        System.out.println(intArrays);
        for (int x : intArrays) {
            System.out.print(x + "\t");
        }
        System.out.println();
        System.out.println("==================================");

        // 二分查找
        int tofineNum = 500;
        int index = Arrays.binarySearch(intArrays, tofineNum);
        System.out.println("结果索引为:" + index + "\t" + (index < 0 ? Integer.MIN_VALUE : intArrays[index]));
        System.out.println("==================================");

        // 对对象数组进行排序
        Person[] ps = genManyPerson();
        Arrays.sort(ps);
        for (Person p : ps) {
            System.out.println(p);

        }

    }

    static Person[] genManyPerson() {
        Person[] ps = new Person[10];
        Random r = new Random();
        String[] hts = new String[] { "浏阳", "衡阳", "长沙", "株洲", "常德", "岳阳" };

        int cha = 0x9FA5 - 0x4E00;
        int i = 0;
        for (i = 0; i < 10; i++) {
            int name1 = r.nextInt(cha) + 0x4E00;
            int name2 = r.nextInt(cha) + 0x4E00;
            String name = (char) name1 + " " + (char) name2;
            int age = r.nextInt(61) + 20;
            char sex = r.nextInt(2) == 0 ? '男' : '女';
            int length = hts.length;
            int index = r.nextInt(length);
            String homeTown = hts[index];
            Person p = new Person(i + 1, name, homeTown);
            ps[i] = p;
        }
        return ps;
    }
}

class Person implements Comparable {// 如果要实现排序,就必须要实现Comparable接口
    private int id;
    private String name;
    private String homeTown;

    public Person() {
        super();
    }

    public Person(int id, String name, String homeTown) {
        super();
        this.id = id;
        this.name = name;
        this.homeTown = homeTown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", homeTown=" + homeTown + "]";
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new RuntimeException("null对象不能比较!");
        }
        if (!(o instanceof Person)) {
            throw new RuntimeException("不是一个类型的对象!");
        }
        Person p = (Person) o;
        if (this.homeTown.compareTo(p.homeTown) == 0) {
            return this.id - p.id;
        } else {
            return this.homeTown.compareTo(p.homeTown);
        }
    }

}

