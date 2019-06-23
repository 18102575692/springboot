package com.example.demo1.dao.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.demo1.dao.mapper.DishMapper;
import com.example.demo1.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DishDao {

    @Autowired
    DishMapper dishMapper;

    /**
     * 添加菜品
     * @param dish 菜信息
     * @return 影响行数
     */
    public int create(Dish dish){
        return this.dishMapper.insertSelective(dish);
    }

    /**
     * 删除菜品
     * @param dish 菜信息
     * @return 影响行数
     */
    public int delete(Dish dish){
        return this.dishMapper.delete(dish);
    }

    /**
     * 更新菜品信息
     * @param dish 菜品信息
     * @return 影响行数
     */
    public int update(Dish dish){
        return this.dishMapper.updateByPrimaryKeySelective(dish);
    }

    /**
     * 菜品详情
     * @param dish 菜品信息
     * @return 菜数据
     */
    public Dish info(Dish dish){
        return this.dishMapper.selectOne(dish);
    }

    /**
     * 菜品列表
     * @param dish 菜品信息
     * @return 列表
     */
    public List<Dish> dishList(Dish dish){
        return this.dishMapper.select(dish);
    }

    //获取最新推荐
    public List<Map<String,Object>> getNewWeekDishList(){
        List<Map<String,Object>> mapList = this.dishMapper.getNewWeekDishList();
        mapList.forEach(obj -> {
            obj.put("main_material", JSONObject.parse(obj.get("main_material")+""));
            obj.put("other_materials", JSONObject.parse(obj.get("other_materials")+""));
            obj.put("dish_describe", JSONObject.parse(obj.get("dish_describe")+""));
        });
        return mapList;
    }

}
