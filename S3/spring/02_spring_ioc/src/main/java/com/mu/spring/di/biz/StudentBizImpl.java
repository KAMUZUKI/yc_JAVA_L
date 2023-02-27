package com.mu.spring.di.biz;

import com.mu.spring.di.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 15:49
 **/

@Service
public class StudentBizImpl{

    private StudentBizImpl(){
        System.out.println("StudentBizImpl Constructor");
    }

    @PostConstruct
    private void init(){
        System.out.println("StudentBizImpl init");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("StudentBizImpl destroy");
    }

    @Autowired
    private StudentDao studentDao;

    public void add(String name) {
        System.out.println("add student: " + name);
    }
}
