package com.example.demo1.controller;

import com.example.demo1.controller.form.UserForm;
import com.example.demo1.controller.group.UserCreate;
import com.example.demo1.entity.User;
import com.example.demo1.service.UserService;
import com.example.demo1.tools.BeanUtil;
import com.example.demo1.tools.Generate;
import com.example.demo1.tools.ResultDto;
import com.example.demo1.tools.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //添加用户
    @RequestMapping("/add")
    public ResultDto addUSer(@Validated(UserCreate.class) UserForm form) {
        Map<String, Object> map = BeanUtil.object2Map(form);
        //TODO 检查是否存在用户名 手机号码相同的用户
        User user = BeanUtil.map2Object(map, User.class);
        user.setCreateTime(TimeUtil.getTime());
        user.setUpdateTime(TimeUtil.getTime());
        user.setId(Generate.getId());
        user.setStatus(1);
        System.out.println(user.toString());
        int result = this.userService.createUser(user);
        if (result == 1) {
            return ResultDto.ok("1");
        } else {
            return ResultDto.error("创建失败");
        }
    }
    @GetMapping("/{id}")
    public ResultDto getUser(@PathVariable("id")String id){
        User user = this.userService.getUser(id);
        if (user != null){
            return ResultDto.ok(user);
        }else {
            return ResultDto.error("没找到用户.");
        }
    }

}
