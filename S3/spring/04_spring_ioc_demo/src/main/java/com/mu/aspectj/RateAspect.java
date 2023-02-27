package com.mu.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 15:45
 **/

public class RateAspect {
    @Pointcut("execution(* com.mu.service..save*(..))")
    private void abc(){}

    @Around("abc()")
    public Object show4(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("RateAspect进来了");
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("方法运行了:" + (end - start));
        return obj;
    }
}
