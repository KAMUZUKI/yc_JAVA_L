package com.mu.aspectj;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 15:25
 **/

public class HiAspect implements Ordered {
    @Pointcut("execution(* com.mu.service..save*(..))")
    private void abc(){}

    @Before("abc()")
    public void doAccessCheck() {
        System.out.println("HiAspect");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
