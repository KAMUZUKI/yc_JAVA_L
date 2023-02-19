package com.mu.spring.Test4_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 此注解表明此类为 业务层的类
 * @author : MUZUKI
 * @date : 2023-01-10 17:58
 */

@Service
public class ProductBizImpl implements ProductBiz{
    /**
     * 由spring 自动从容器取出 ProductDao的一个实现类的对象 注入
     * 业务层依赖dao层， 但最好是依赖接口
     */
    @Autowired
    private ProductDao productDao;

    public ProductBizImpl(){
        System.out.println("ProductBizImpl init");
    }

    @Override
    public void takein() {
        productDao.find();
        int i = 1;
        if (i==1){
            productDao.update();
        }else {
            productDao.add();
        }
    }
}
