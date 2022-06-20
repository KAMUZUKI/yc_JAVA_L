package src.com.mu.project17_lang;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test_reflection {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // 反射：一种类的自省机制
        //      一个类的基因

        //前提这个Person是第三方公司，已经编译成class字节码

        //创建这个类
        Class c = Class.forName("src.com.mu.");
        //c就是这个Person2的基因
        //下面通过基因来了解这个类
        Field[] fs = c.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            System.out.println(f.getType() + "\t" + f.getName());
        }
        //取方法
        Method[] ms = c.getDeclaredMethods();
        for (int i = 0; i < ms.length; i++) {
            Method m = ms[i];
            System.out.println(m.getName());
        }

        // 创建对象，不要new
        Object o = c.newInstance();
        System.out.println(0);

        //还想给o对象设置 name,id
        for (int i = 0; i < ms.length; i++) {
            Method m = ms[i];
            if ("setName".equals(m.getName())){
                //激活setName方法，给一个值
                m.invoke(o,"神奇小子");
            }
            if ("setId".equals(m.getName())){
                m.invoke(o,1);
            }
        }
        System.out.println(0);
    }
}

class Person2{
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}
