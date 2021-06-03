package com.xxx.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.server.config.security.component.JwtTokenUtil;
import com.xxx.server.mapper.MenuRoleMapper;
import com.xxx.server.mapper.RoleMapper;
import com.xxx.server.mapper.UserMapper;
import com.xxx.server.pojo.*;
import com.xxx.server.service.IMenuRoleService;
import com.xxx.server.service.IUserService;
import com.xxx.server.utils.HttpURLConnectionDemo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IUserService userService;
   @Autowired
   private MenuRoleMapper menuRoleMapper;
   @Autowired
   private  HttpURLConnectionDemo httpURLConnectionDemo;

   @Autowired
   private UserMapper userMapper;

   @Autowired
   private UserDetailsService userDetailsService;

   @Autowired
   private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private  String tokenHead;

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.appsecert}")
    private String appsecert;


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

        List<String> Menu = new ArrayList<>();
        List<MenuRole> menuRoles = menuRoleMapper.selectMenusByRoleId(user.getRoleId());
        for (MenuRole menuRole : menuRoles) {
            Menu.add(menuRole.getMenu().getComponent());
        }


        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        Map<String,Object> data = new HashMap<>();
        if(user.getRole() == 1){
            String[] roles = {"admin"};
            data.put("roles",roles);
        }else{
            data.put("roles",Menu);
        }

        data.put("user",user);
        result.put("data",data);
        return result;

    }
    @ApiOperation(value="text")
    @GetMapping("text")
    public RespBean text() {
        return RespBean.success("text");
    }

    @ApiOperation(value="获取微信openid")
    @GetMapping("openid")
    public RespBean  openid(@RequestParam(required = false) Map params){
        String url ="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appsecert+"&js_code="+ params.get("code") +"&grant_type=authorization_code";
        Map map = httpURLConnectionDemo.doGet(url);
        String openid = (String)map.get("openid");
        User user = userService.getAdminByUserName(openid);
        if(user == null){
            user = new User();
            user.setEnabled(true);
            user.setUsername(openid);
            user.setOpenid(openid);
            user.setPassword("123456");
            user.setRole(2);
            UserInfo userInfo = new UserInfo();
            userService.create(userInfo,user,null);

        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(openid);
        String token = jwtTokenUtil.generateToken(userDetails);
        params.put("openid", openid);
        params.put("token", token);
        params.put("tokenHead", tokenHead);
        return RespBean.success("成功",params);
    }

}
