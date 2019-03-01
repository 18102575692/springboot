package com.example.demo1.dao.dao;

import com.example.demo1.dao.mapper.UserMapper;
import com.example.demo1.entity.User;
import com.example.demo1.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    /**
     * 获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    public Optional<User> getUser(String id){
        return this.userMapper.findById(id);
    }

    /**
     * 获取用户列表
      * @param map  条件
     * @return 用户列表
     */
    public List<User> getUserList(Map<String,Object> map){
//        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher()
        User user = BeanUtil.map2Object(map,User.class);
        Example<User> example  = Example.of(user);
        return this.userMapper.findAll(example);
    }
}
