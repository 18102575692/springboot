package com.example.demo1.dao.dao;

import com.example.demo1.dao.mapper.UserMapper;
import com.example.demo1.entity.User;
import com.example.demo1.tools.Pager;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserDao {
    @Autowired
    UserMapper userMapper;

    /**
     * 添加用户
     * @param user 用户信息
     * @return 返回的 保存数据
     */
    public int create(User user){
        return this.userMapper.insert(user);
    }

    /**
     * 获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    public User getUser(String id){
        return this.userMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取用户列表
      * @param user  条件
     * @param pager 分页相关
     * @return 用户列表
     */
    public List<User> getUserList(User user, Pager pager){
        Example example = new Example(User.class);
        if (!StringUtils.isEmpty(user.getName())){
            example.createCriteria().andLike("name",user.getName());
        }
        if (!StringUtils.isEmpty(user.getPhone())){
            example.createCriteria().andLike("phone",user.getPhone());
        }
        example.orderBy("created_time").desc();
        RowBounds rowBounds=new RowBounds(pager.getCurrentPage(),pager.getPageSize());
        return this.userMapper.selectByExampleAndRowBounds(example,rowBounds);
    }
    public int getUserListToCount(User user, Pager pager){
        Example example = new Example(User.class);
        if (!StringUtils.isEmpty(user.getName())){
            example.createCriteria().andLike("name",user.getName());
        }
        if (!StringUtils.isEmpty(user.getPhone())){
            example.createCriteria().andLike("phone",user.getPhone());
        }
        return this.userMapper.selectCountByExample(example);
    }


    /**
     * 更新用户
     * @param user 用户
     * @return 影响行数
     */
    public int updateUser(User user){
        return this.userMapper.updateByPrimaryKeySelective(user);
    }
}
