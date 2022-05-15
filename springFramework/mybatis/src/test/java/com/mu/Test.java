package com.mu;

import com.mu.controller.Controller;
import com.mu.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("UserMapper.xml");
        User user = (User) context.getBean("Username");
        Controller controller= (Controller) context.getBean("UserControllerImpl");
        System.out.println(controller.getName());
        System.out.println("用户名为：" + user);
    }
}
