package com.example.demo1.controller;

import com.example.demo1.service.RedisService;
import com.example.demo1.tools.ImageVerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class IndexController {
    @Autowired
    RedisService redisService;

    @RequestMapping("/code")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageVerificationCode ivc = new ImageVerificationCode();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage();  //获取验证码
        //存储到redis sessionID ivc.getText()
        this.redisService.set(request.getSession().getId(),ivc.getText(),500L);
        ImageVerificationCode.output(image, response.getOutputStream());//将验证码图片响应给客户端
    }
}
