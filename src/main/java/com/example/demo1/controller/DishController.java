package com.example.demo1.controller;

import com.example.demo1.service.DishService;
import com.example.demo1.tools.JsoupTools;
import com.example.demo1.tools.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    DishService dishService;
    @Autowired
    JsoupTools jsoupTools;

    @RequestMapping("/new_week_dish_list")
    public ResultDto getNewWeekDishList(){
        return new ResultDto(200,"success",this.dishService.getNewWeekDishList());
    }
    //立即爬取
    @RequestMapping("/get_week_dish")
    public String getDish() throws IOException, InterruptedException {
        this.jsoupTools.getDish();
        return "已执行";
    }
}
