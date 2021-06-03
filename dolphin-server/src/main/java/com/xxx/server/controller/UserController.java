package com.xxx.server.controller;


import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.server.annotation.MultiRequestBody;
import com.xxx.server.pojo.*;
import com.xxx.server.service.IUserService;
import com.xxx.server.utils.GetRequestJsonUtils;
import com.xxx.server.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-01
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private IUserService userService;
    @ApiOperation("查看所有会员")
    @GetMapping("/lists")
    public Map getLists(@RequestParam(required = false) Map params){
        params.put("role",2);

        PageHelper.startPage(1, 15, true);
        List<UserInfo> list = userService.getLists(params);
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(list);
        Map lists = RespBean.getLists(pageInfo, list);
        return lists;
    }
    @ApiOperation("新增会员")
    @PostMapping("/create")
    public RespBean  create(@Valid @MultiRequestBody UserInfo userInfo,  @Valid @MultiRequestBody User user) {
        user.setRole(2);
        user.setShopId(Utils.getUser().getShopId());
        System.out.println("shop_id"+user.getShopId());
        RespBean result =  userService.create(userInfo,user,null);
        return result;
    }
    @ApiOperation("修改会员")
    @PostMapping("/edit")
    public  RespBean edit(@Valid @MultiRequestBody UserInfo userInfo,  @Valid @MultiRequestBody User user){
        user.setShopId(Utils.getUser().getShopId());
        RespBean result =  userService.edit(userInfo,user,null);
        return result;
    }
    @ApiOperation("删除会员")
    @PostMapping("/del")
    public  RespBean del(@MultiRequestBody User user){
          return userService.del(user);
    }

    @ApiOperation("删除全部")
    @PostMapping("/delAll")
    public RespBean delAll(@RequestBody Map map){
        return null;

    }


}
