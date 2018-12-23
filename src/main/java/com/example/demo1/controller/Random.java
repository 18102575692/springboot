package com.example.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**获取服务器的随机数字
 * @Author: xieshaojun
 * @Date: 2018-11-18 10:13
 * @Version 1.0
 */
@Component
@RestController
@RequestMapping("/random")
public class Random {
    @Value("${random.number}")
    String number;
    @Autowired
    RedisTemplate redisTemplate;
    @RequestMapping("/number")
    private String number(){
        redisTemplate.convertAndSend("ts","hello");
        return number;
    }
}
