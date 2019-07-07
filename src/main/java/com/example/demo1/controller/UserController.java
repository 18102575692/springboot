package com.example.demo1.controller;

import cn.hutool.crypto.SmUtil;
import com.example.demo1.controller.form.UserForm;
import com.example.demo1.controller.group.UserCreate;
import com.example.demo1.dao.mapper.DishMapper;
import com.example.demo1.entity.User;
import com.example.demo1.service.RedisService;
import com.example.demo1.service.UserService;
import com.example.demo1.tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    //添加用户()
    @RequestMapping("/add")
    public ResultDto addUSer(@Validated(UserCreate.class) UserForm form) {
        Map<String, Object> map = BeanUtil.object2Map(form);
        //TODO 检查是否存在用户名 手机号码相同的用户
        User user = BeanUtil.map2Object(map, User.class);
        user.setCreateTime(TimeUtil.getTime());
        user.setUpdateTime(TimeUtil.getTime());
        user.setId(Generate.getId());
        user.setStatus(1);
        user.setPassword(SmUtil.sm3(user.getPassword()));
        int result = this.userService.createUser(user);
        if (result == 1) {
            return ResultDto.ok("创建成功");
        } else {
            return ResultDto.error("创建失败");
        }
    }

    @GetMapping("/{id}")
    public ResultDto getUser(@PathVariable("id") String id) {
        User user = this.userService.getUser(id);
        Map<String, Object> map = BeanUtil.object2Map(user);
        map.remove("password");
        if (user != null) {
            return ResultDto.ok(map);
        } else {
            return ResultDto.error("没找到用户.");
        }
    }

    @GetMapping("/list")
    public ResultDto getUserList(@RequestParam(value = "name",required = false)String name,
                                 @RequestParam(value = "phone",required = false)String phone,
                                 @RequestParam(value = "page",required = false)Integer page,
                                 @RequestParam(value = "page_size",required = false)Integer page_size){
        User user = new User();
        if (!StringUtils.isEmpty(name)){
            user.setName(name);
        }
        if (!StringUtils.isEmpty(phone)){
            user.setPhone(phone);
        }
        Pager<User> pager = new Pager<>();
        pager.setTotalRecord(this.userService.getUserToCount(user));
        if (StringUtils.isEmpty(page)){
            pager.setPageSize(0);
        }else {
            pager.setPageSize(page);
        }
        if (StringUtils.isEmpty(page_size)){
            pager.setPageSize(10);
        }else {
            pager.setPageSize(page_size);
        }
        pager.setDataList(this.userService.getUSerList(user,pager));
        return ResultDto.ok(pager);

    }
    //二维码
    @GetMapping("/test")
    public String test(HttpServletResponse servletResponse){
        QrCode qrCode=new QrCode();
        String Name="测试包";
        String[] file = {"http://127.0.0.1:8080/img/mc_index.jpg","http://127.0.0.1:8080/img/mc_index.jpg"};
                qrCode.download(servletResponse,Name,file);
        return "ok";
    }
    @Autowired
    DishMapper mapper;
    @RequestMapping("/A")
    public String getList(HttpServletRequest request){
        System.out.println("方法");
        return this.redisService.get(request.getSession().getId())+"";
    }

    //登录接口
    @RequestMapping(name="/login",method=RequestMethod.POST)
    public ResultDto login(@RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam("code")String code){

        return ResultDto.ok();
    }
}
