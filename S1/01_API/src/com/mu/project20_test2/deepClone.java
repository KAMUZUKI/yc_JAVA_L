package src.com.mu.project20_test2;

import java.io.*;
import java.util.Objects;

// 场景：需保证对象有多个副本的时候

public class deepClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person("zhangsan",23,new Pet("CoCo",3));
        Person person2 = (Person) person.clone();
        System.out.println("original:" + person.hashCode() + "   clone:" + person2.hashCode());
        System.out.println(person);
        System.out.println(person2);
        person2.name = "lisi";
        person2.age = 24;
        person2.pet.name = "Momo";
        person2.pet.age = 4;
        System.out.println("=====================================");
        System.out.println(person);
        System.out.println(person2);
    }
}

