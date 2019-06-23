package com.example.demo1.dao.mapper;

import com.example.demo1.entity.Dish;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface DishMapper extends Mapper<Dish> {


    List<Map<String,Object>> getDishList();

    List<Map<String,Object>> getNewWeekDishList();
}
