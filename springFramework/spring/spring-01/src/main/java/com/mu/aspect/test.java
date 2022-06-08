package com.mu.aspect;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author pdai
 */
@Aspect
public class test {

    /**
     * aspect for every methods under service package.
     */
    public void  test(){
        System.out.println("test is running...");
    }

}
