package com.mu.jdkproxy;

/**
 * @author : MUZUKI
 * @date : 2023-02-26 16:28
 **/

public class HelloImpl implements HelloI {
    @Override
    public void sayHello() {
        System.out.println("HelloImpl中的Hello()");
    }

    @Override
    public void showBye() {
        System.out.println("HelloImpl中的showBye()");
    }
}
