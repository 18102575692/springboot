package com.example.demo1.controller;

import com.example.demo1.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试redis发布订阅功能
 *
 * @Author: xieshaojun
 * @Date: 2018-12-23 15:02
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/redis", method = RequestMethod.POST)
public class pushController {
    @Autowired
    RedisService redisService;

    @RequestMapping("/push")
    public Map<String, Object> push(@RequestParam("channel") String channel, @RequestParam("msg") String message) {
        this.redisService.sendChannelMess(channel, message);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        return map;
    }
}
