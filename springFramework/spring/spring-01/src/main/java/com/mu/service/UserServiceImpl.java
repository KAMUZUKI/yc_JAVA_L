package com.mu.service;

import com.mu.entity.User;
import com.mu.dao.UserDaoImpl;

import java.util.List;

public class UserServiceImpl {

    /**
     * user dao impl.
     */
    UserDaoImpl userDao;

    /**
     * init.
     */
    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    /**
     * find user list
     *
     * @return user list
     */
    public List<User> findUserList(){
        return userDao.findUserList();
    }

    /**
     * set dao.
     *
     * @param userDao user dao
     */
    public void setUserDao(UserDaoImpl userDao){
        this.userDao = userDao;
    }
}
