package com.example.demo1.controller;

import com.example.demo1.service.UserService;
import com.example.demo1.tools.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //添加用户
    @RequestMapping("/add")
    public ResultDto addUSer() {
        System.out.println("测试输出");
        return ResultDto.ok("1");
    }

}
