package com.mu.controller;

import com.mu.dao.UserImpl;

public class Controller implements UserImpl {
    private String name;
    public Controller(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("controller is running...");
    }
}
