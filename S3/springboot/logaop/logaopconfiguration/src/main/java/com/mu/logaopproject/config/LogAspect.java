package com.mu.logaopproject.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author : MUZUKI
 * @date : 2023-03-12 15:50
 **/

@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("execution(* *..*Controller.*(..))")
    private void abc(){}

    @Before("abc()")
    public void recordRequest(JoinPoint jp){
        ServletRequestAttributes ras = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ras.getRequest();
        log.info("请求方的地址：", request.getRequestURL());
        log.info("请求方的address：", request.getRemoteAddr());
        Map<String,String[]> map = request.getParameterMap();
        log.info("请求参数：", Arrays.toString(map.entrySet().toArray()));
        log.info("控制器中的方法" + jp.getSignature().toString() + "对应的参数:" + Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(returning = "a", pointcut = "abc()")
    public void recordRequestReturnValue(Object a){
        log.info("控制器中的方法的返回值：", a);
    }
}
