package com.example.demo1.dao.mapper;

import com.example.demo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户基本信息
 */

public interface UserMapper extends CrudRepository<User,String>, JpaRepository<User,String> {

}
