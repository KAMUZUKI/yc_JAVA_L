package com.mu.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 21:04
 **/

@Component
@Aspect
public class HelloWorldAspect {
    @Pointcut("execution(* com.mu.service..save*(..))")
    public void abc(){}

//    @Around("pointcut()")
//    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("环绕通知开始");
//        // 执行目标方法。
//        proceedingJoinPoint.proceed();
//        System.out.println("环绕通知结束");
//    }
//
//    @Before("pointcut()")
//    public void beforeAdvice(){
//        System.out.println("前置通知")
//    }
//
//    @AfterReturning("pointcut()")
//    public void afterReturningAdvice(){
//        System.out.println("后置通知");
//    }
//
//    @AfterThrowing("pointcut()")
//    public void afterThrowingAdvice(){
//        System.out.println("异常通知");
//    }
//
//    @After("pointcut()")
//    public void afterAdvice(){
//        System.out.println("最终通知");
//    }

    @Around("abc()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before...");
        System.out.println("正在操作方法: " + joinPoint.getSignature().getName());
        joinPoint.proceed();
        System.out.println("around after...");
    }

    @Before("abc()")
    public void before() {
        System.out.println("before...");
    }

    @After("abc()")
    public void after(){
        System.out.println("after...");
    }
}
