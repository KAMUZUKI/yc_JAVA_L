package com.mu.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 15:30
 **/

@Aspect
@Component
public class GetJoinPointInfo {
    @Pointcut("execution(* com.yc.service..save*(..))")
    public void showInfo(JoinPoint joinPoint){
        System.out.println("连接点信息如下：");
        System.out.println("目标类：" + joinPoint.getTarget() + ",方法的签名" + joinPoint.getSignature());
        System.out.println("加了增强的方法中的参数值:");

        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                System.out.println(arg);
            }
        }
    }
}
