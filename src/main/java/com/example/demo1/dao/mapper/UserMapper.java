package com.example.demo1.dao.mapper;

import com.example.demo1.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户基本信息
 */
@Repository
public interface UserMapper extends Mapper<User> {

}
