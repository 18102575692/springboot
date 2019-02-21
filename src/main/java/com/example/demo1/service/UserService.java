package com.example.demo1.service;

import com.example.demo1.dao.dao.UserDao;
import com.example.demo1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    /**
     * 添加用户
     * @param user  用户信息
     * @return 影响行数
     */
    public int createUser(User user){
        User user1 =this.userDao.create(user);
        if (user1 !=  null){
            return 1;
        }
        return 0;
    }
}
