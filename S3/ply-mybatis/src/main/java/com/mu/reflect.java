package com.mu;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author : MUZUKI
 * @date : 2022-12-10 19:11
 **/

public class reflect {
    public static void main(String[] args) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.mu.A");
            A a = new A();
            Class<? extends A> aClass1 = a.getClass();
            Class<A> aClass2 = A.class;
            System.out.println(aClass == aClass1);
            System.out.println(aClass1 == aClass2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(aClass);
    }

    public void test() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        C c = C.class.newInstance();
        final Constructor<C> constructor = C.class.getConstructor(String.class);
        //带参数的创建对象
        c = constructor.newInstance("张三");

        //属性的设值与取值
        final Field age = C.class.getField("age");
        age.set(c, 18);
        System.out.println(age.get(c));

        //获取父类对象
        final Class<? super C> bClass = C.class.getSuperclass();
        final Class<? super C> aClass = bClass.getSuperclass();

        final Field name = aClass.getDeclaredField("name");
        name.set(c, "李四");
        System.out.println(name.get(c));

        // protected 属性
        final Field age1 = aClass.getDeclaredField("age");
    }
}


class A {
    public String name;
    private int age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}

class B extends A {
    private String work;

    public String getWork() {
        return work;
    }
}

class C extends B {
    public C() {
    }

    public C(String name){
        this.name = name;
    }
}