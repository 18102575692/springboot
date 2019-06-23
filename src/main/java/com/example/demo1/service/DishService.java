package com.example.demo1.service;

import com.example.demo1.dao.dao.DishDao;
import com.example.demo1.entity.Dish;
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
}
