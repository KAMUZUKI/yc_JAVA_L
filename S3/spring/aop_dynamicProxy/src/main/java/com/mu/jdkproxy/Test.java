package com.mu.jdkproxy;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 16:37
 **/

public class Test {
    public static void main(String[] args) {
        //配置将代理类的字节码保存在本地
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //目标类
        HelloImpl hello = new HelloImpl();
        CustomInvocationHandler handler = new CustomInvocationHandler(hello);
        //生成代理类
        Object proxy = handler.createProxy();
        System.out.println("proxy.getClass() = " + proxy.getClass());

        HelloI hi = (HelloI) proxy;
        hi.sayHello();
        hi.showBye();
    }
}
