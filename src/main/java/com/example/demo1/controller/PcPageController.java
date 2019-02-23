package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页接口
 *
 * @author xiesj
 */
@Controller
public class PcPageController {
    @GetMapping("/")
    public String index() {
        return "pages/index.html";
    }
}
