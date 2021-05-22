package com.xxx.server.controller;

import com.xxx.server.pojo.User;
import com.xxx.server.pojo.UserLoginParam;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IUserService userService;
    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(UserLoginParam userLoginParam, HttpServletRequest request){
        System.out.println(userLoginParam);
        return userService.login(
                userLoginParam.getUsername(),
                userLoginParam.getPassword(),
                userLoginParam.getCode(),
                request);
    }
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        return  RespBean.success("注销成功");
    }

    @ApiOperation(value="获取用户当前登录的信息")
    @PostMapping("/userinfo")
    public Map userInfo(Principal principal){
        if(null == principal){
            return null;
        }
        String username = principal.getName();
        User user = userService.getAdminByUserName(username);
        user.setPassword(null);
        user.setRoles(userService.getRoles(user.getId()));

        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        Map<String,Object> data = new HashMap<>();
        String[] roleArr = {"admin"};
        data.put("roles",roleArr);
        data.put("user",user);
        result.put("data",data);
        return result;

    }
    @ApiOperation(value="text")
    @GetMapping("text")
    public RespBean text() {
        return RespBean.success("text");
    }
}
