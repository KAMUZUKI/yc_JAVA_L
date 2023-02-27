package com.mu.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 16:27
 **/

public class CustomInvocationHandler implements InvocationHandler {
    /**
     * 目标类
     */
    private Object target;

    public CustomInvocationHandler(Object target) {
        this.target = target;
    }

    public Object createProxy() {
        //jdk中提供了 Proxy 类，有一个方法专门用于根据接口创建代理类的方法
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return proxy;
    }

    /**
     * @param proxy  代理类
     * @param method 调用的方法
     * @param args  方法的参数值
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("CustomInvocationHandler进来了");
        if (method.getName().indexOf("say")>=0) {
            showTime();
        }
        //反射机制调用目标类的方法
        Object obj = method.invoke(target, args);
        return obj;
    }

    public void showTime() {
        System.out.println("当前时间:" + new Date());
    }
}
