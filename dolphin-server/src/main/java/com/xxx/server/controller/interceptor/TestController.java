package com.xxx.server.controller.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
public class TestController {
    @GetMapping("test")
    public String getTest(){
        return "1";
    }
}
