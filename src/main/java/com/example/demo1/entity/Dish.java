package com.example.demo1.entity;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 一道菜
 * @author xiesj
 */
@Data
@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    private static final long serialVersionUID = -8553205799504170641L;
    @Id
    @Column(name = "dish_id",columnDefinition = "varchar(6) comment '菜ID'")
    String dish_id;
    @Column(name = "dish_name",columnDefinition = "varchar(80) comment '菜名称'",nullable = false)
    String dish_name;
    @Column(name = "image_url",columnDefinition = "varchar(200) comment '主图地址'")
    String image_url;
    @Column(name = "main_material",columnDefinition = "text comment '主料'")
    String main_material;
    @Column(name = "other_materials",columnDefinition = "text comment '辅料'")
    String other_materials;
    @Column(name = "dish_taste",columnDefinition = "varchar(10) comment '口味'")
    String dish_taste;
    @Column(name = "time_consuming",columnDefinition = "varchar(10) comment '耗时'")
    String time_consuming;
    @Column(name = "dish_difficulty",columnDefinition = "varchar(10) comment '难度'")
    String dish_difficulty;
    @Column(name = "dish_technology",columnDefinition = "varchar(10) comment '工艺'")
    String dish_technology;
    @Column(name = "dish_describe",columnDefinition = "text comment '描述'")
    String dish_describe;
    //周期
    @Column(name = "week_time",columnDefinition = "int(6) comment '周期（年月日）'")
    Integer week_time;
}
