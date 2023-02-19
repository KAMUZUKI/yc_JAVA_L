package com.mu.spring.Test3_factoryBean;

/**
 * @author : MUZUKI
 * @date : 2023-01-10 17:56
 **/

public class ProductMybatisDaoImpl implements ProductDao {
    public ProductMybatisDaoImpl(){
        System.out.println("ProductMybatisDaoImpl init");
    }

    @Override
    public void add() {
        System.out.println("ProductMybatisDaoImpl add");
    }

    @Override
    public void find() {
        System.out.println("ProductMybatisDaoImpl find");
    }

    @Override
    public void update() {
        System.out.println("ProductMybatisDaoImpl update");
    }
}
