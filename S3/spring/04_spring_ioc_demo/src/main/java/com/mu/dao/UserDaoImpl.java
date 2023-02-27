package com.mu.dao;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 17:17
 **/

@Repository()
public class UserDaoImpl implements UserDao{
    private static final Log logger = LogFactory.getLog(UserDaoImpl.class.getName());

    @Autowired
    private DataSource dataSource;

    @Override
    public void save(){
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into user(username,password) values('muzuki','123456')");
            int res = preparedStatement.executeUpdate();
            logger.info("插入数据成功" + res);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
