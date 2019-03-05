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
      //TODO 去重
        return this.userDao.create(user);
    }

    /**
     * 获取用户详情
     * @param id  用户ID
     * @return 用户记录
     */
    public User getUser(String id){
        return this.userDao.getUser(id);
    }
}
