package com.example.demo1.dao.dao;

import com.example.demo1.dao.mapper.UserMapper;
import com.example.demo1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
    @Autowired
    UserMapper userMapper;

    /**
     * 添加用户
     * @param user 用户信息
     * @return 返回的 保存数据
     */
    public User create(User user){
        return this.userMapper.save(user);
    }
}
