package com.mu.pojo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("userbean.xml");
        User user1 = ctx.getBean("usernameByP",User.class);
        User user2 = ctx.getBean("usernameByP",User.class);
        System.out.println("user1 == user2" + (user1 == user2));  //true

        User user3 = ctx.getBean("usernameByC",User.class);
        User user4 = ctx.getBean("usernameByC",User.class);
        System.out.println("user3 == user4" + (user3 == user4));  //false
    }
}
