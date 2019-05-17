package com.example.demo1.service;

import com.example.demo1.dao.dao.DishDao;
import com.example.demo1.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
