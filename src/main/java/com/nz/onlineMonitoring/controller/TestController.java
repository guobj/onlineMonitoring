package com.nz.onlineMonitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "我是一个测试";
    }
}
