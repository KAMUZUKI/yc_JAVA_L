package com.mu.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 15:36
 **/

@Component
@Aspect
@Order(1)
public class TestAspect {
    @Pointcut("execution(* com.mu.service..save*(..))")
    public void abc(){}

    @AfterReturning(pointcut = "abc()", returning = "result")
    public void afterReturning(Object result) {
        System.out.println("afterReturning > 被增强的方法的返回结果为: " + result);
    }

    @AfterThrowing(pointcut = "abc()", throwing = "ex")
    public void afterThrowing(Exception ex) {
        System.out.println("afterThrowing > 被增强的方法的方法有异常报处: " + ex);
    }

    @After("abc()")
    public void after() {
        System.out.println("after");
    }

    @Around("abc()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("在原方法执行前执行");
        Object obj = joinPoint.proceed();
        System.out.println("在原方法执行后执行");
        return obj;
    }
}
