package com.mu.dao;

import org.muframework.annotation.Component;
import org.muframework.annotation.Lazy;
import org.muframework.annotation.Scope;

/**
 * @author : MUZUKI
 * @date : 2023-03-07 20:13
 **/

@Component
@Lazy
@Scope("prototype")
public class PersonDaoImpl implements PersonDao{
    @Override
    public void add() {
        System.out.println("add");
    }

    @Override
    public void find() {
        System.out.println("find");
    }
}
