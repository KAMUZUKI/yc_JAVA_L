package com.mu.service;

import com.mu.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author : MUZUKI
 * @date : 2023-02-25 17:23
 **/

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public void save() {
        try {
            userDao.save();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveTest(){
        System.out.println("saveTest");
    }

    @Override
    public int addResuser(String name){
        Random r = new Random();
        int i = r.nextInt(100);
        if (i==0){
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("addResuser");
        return 9999;
    }
}
