package com.example.demo1.controller;

import org.springframework.beans.factory.annotation.Value;
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
    @RequestMapping("/number")
    private String number(){
        return number;
    }
}
