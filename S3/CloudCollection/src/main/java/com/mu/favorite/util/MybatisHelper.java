package com.mu.favorite.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author : MUZUKI
 * @date : 2022-12-30 14:53
 **/

public class MybatisHelper {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        try(InputStream inputStream = Resources.getResourceAsStream(resource)){
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }
}
