package com.example.demo1.service;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo1.dao.dao.DishDao;
import com.example.demo1.entity.Dish;
import com.example.demo1.tools.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DishService {
    @Autowired
    DishDao dishDao;

    /**
     * 创建菜品
     * @param dish 菜
     * @return 影响行数
     */
    public int create(Dish dish){
        return this.dishDao.create(dish);
    }

    /**
     * 获取最新的期的推荐列表
     */
    public List<Map<String,Object>> getNewWeekDishList(){
        return this.dishDao.getNewWeekDishList();
    }
    /**
     * 获取菜的详细数据
     */
    public Map<String,Object>  getDishById(String id){
        Dish dish  = new Dish();
        dish.setDish_id(id);
        Dish dishResult = this.dishDao.info(dish);
        if (dishResult == null){
            return null;
        }
        return BeanUtil.beanToMap(dishResult);
    }
}
