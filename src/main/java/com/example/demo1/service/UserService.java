package com.example.demo1.service;

import com.example.demo1.dao.dao.UserDao;
import com.example.demo1.dao.mapper.UserMapper;
import com.example.demo1.entity.User;
import com.example.demo1.tools.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserMapper mapper;

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 影响行数
     */
    public int createUser(User user) {
        //去重
        Example.Criteria exampleName = new Example(User.class).createCriteria();
        exampleName.andEqualTo("name", user.getName());
        exampleName.andEqualTo("status", 1);
        List<User> usersName = this.mapper.selectByExample(exampleName);
        if (usersName.size() != 0) {
            return 0;
        }
        Example.Criteria examplePhone = new Example(User.class).createCriteria();
        exampleName.andEqualTo("phone", user.getName());
        exampleName.andEqualTo("status", 1);
        List<User> usersPhone = this.mapper.selectByExample(exampleName);
        if (usersPhone.size() != 0) {
            return 0;
        }
        return this.userDao.create(user);
    }

    /**
     * 获取用户详情
     *
     * @param id 用户ID
     * @return 用户记录
     */
    public User getUser(String id) {
        return this.userDao.getUser(id);
    }

    /**
     * 获取用户列表
     * @param user 用户筛选条件
     * @param pager 分页相关
     * @return 用户列表
     */
    public List<User> getUSerList(User user, Pager pager){
        return this.userDao.getUserList(user, pager);
    }
    public int getUserToCount(User user){
        return this.userDao.getUserListToCount(user);
    }

}
