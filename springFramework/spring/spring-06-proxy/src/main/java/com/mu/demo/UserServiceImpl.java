package com.mu.demo;

public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("add方法被调用");
    }

    @Override
    public void delete() {
        System.out.println("delete方法被调用");
    }

    @Override
    public void update() {
        System.out.println("update方法被调用");
    }

    @Override
    public void query() {
        System.out.println("query方法被调用");
    }
}
