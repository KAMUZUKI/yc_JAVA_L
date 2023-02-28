package org.muframework.annotation;

import java.lang.annotation.*;

/**
 * @author : MUZUKI
 * @date : 2023-02-28 20:09
 **/


@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {
    String[] value() default {};

    String[] name() default {};
}
