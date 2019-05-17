package com.example.demo1.dao.mapper;

import com.example.demo1.entity.Dish;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface DishMapper extends Mapper<Dish> {
}
