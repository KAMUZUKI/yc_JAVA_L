package com.mu;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author : MUZUKI
 * @date : 2022-12-10 20:18
 **/

@Anno1
public class Annotation {
    @Anno2(value = "hello",likes = {"电影","篮球"})
    String name;

    public void test(@Anno1 String msg){
        System.out.println("test");
    }

    public static void main(String[] args) throws NoSuchFieldException {
        final Anno1 annotation = Annotation.class.getAnnotation(Anno1.class);
        System.out.println(annotation);

        final Anno1 annotation2 = SubAnnotation.class.getAnnotation(Anno1.class);
        System.out.println(annotation2);

        final Field name = Annotation.class.getDeclaredField("name");
        final Anno2 anno21 = name.getAnnotation(Anno2.class);

        System.out.println("anno21.value() = " + anno21.value());
        System.out.println("anno21.age() = " + anno21.age());
        System.out.println("anno21.likes() = " + Arrays.toString(anno21.likes()));
    }
}


class SubAnnotation extends Annotation{

}

/**
 * @author MUZUKI
 */
@Target({ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface Anno1{

}

/**
 * @author MUZUKI
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Anno2{
    String value();

    String[] likes();

    int age() default 18;
}
