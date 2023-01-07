package com.mu.net.comcat2.javax.servlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : MUZUKI
 * @date : 2023-01-06 19:24
 **/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WebServlet {
    String value() default "";
}
