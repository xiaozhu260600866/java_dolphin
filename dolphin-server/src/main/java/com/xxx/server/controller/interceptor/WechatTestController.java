package com.xxx.server.controller.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class WechatTestController {
    @GetMapping("test")
    public Map getTest(HttpServletRequest request){
         return (Map)request.getAttribute("userInfo");
    }
}
