package src.com.mu.project17_lang;

public class FruitFactory {
    public Fruit product(String name){
        System.out.println("对水果清洗打蜡");
        if ("apple".equals(name)){
            return new Apple();
        }else if("pear".equals(name)){
            return new Pear();
        }else if("banana".equals(name)){
            return new Banana();
        }else{
            return null;
        }
    }

    public Fruit product2(String classname){
        System.out.println("对水果清洗打蜡");
        Object o = null;
        try {
            Class c = Class.forName(classname);
            o = c.newInstance();
            return (Fruit)o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Fruit product3(Class c){
        System.out.println("对水果清洗打蜡");
        Object o = null;
        try {
            o = c.newInstance();
            return (Fruit)o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Apple a = new Apple();
        FruitFactory ff = new FruitFactory();
//        Fruit f1 = ff.product("apple");
//        System.out.println(f1);

        //方案二：利用反射完成水果的创建
        Fruit f2 = ff.product2("project17_lang.Grape");
        System.out.println(f2);

        //方案三：传class对象
        Fruit f3 = ff.product3(Apple.class);
        System.out.println(f3);
    }
}
