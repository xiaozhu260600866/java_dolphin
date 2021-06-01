package com.xxx.server.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.server.annotation.MultiRequestBody;
import com.xxx.server.pojo.*;
import com.xxx.server.service.IRoleService;
import com.xxx.server.service.IShopService;
import com.xxx.server.service.IUserInfosService;
import com.xxx.server.service.IUserService;
import com.xxx.server.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IShopService shopService;
    @ApiOperation("员工列表")
    @GetMapping("/lists")
    public Map lists(@RequestParam(required = false) Map params){
        PageHelper.startPage(1, 15, true);
        List<UserInfo> list =  userService.getStaffLists(params);
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(list);
        Map lists = RespBean.getLists(pageInfo, list);
        List<Role> roles = roleService.list();
        List<Map<String, Object>> roleArr = Utils.getLabel(roles,"name");
        lists.put("roleArr",roleArr);
        List<Shop> shops = shopService.list();
        List<Map<String, Object>> shopArr = Utils.getLabel(shops,"companyName");
        lists.put("shops",shopArr);
        return lists;
    }
    @ApiOperation("新增员工")
    @PostMapping("create")
    public RespBean create(@Valid @MultiRequestBody UserInfo userInfo, @Valid @MultiRequestBody User user,@MultiRequestBody UserRole userRole){
        user.setRole(3);
        RespBean result =  userService.create(userInfo,user,userRole);
        return result;
    }

    @ApiOperation("修改员工")
    @PostMapping("edit")
    public RespBean edit(@Valid @MultiRequestBody UserInfo userInfo, @Valid @MultiRequestBody User user,@MultiRequestBody UserRole userRole){
        user.setRole(3);
        RespBean result =  userService.edit(userInfo,user,userRole);
        return result;
    }

    @ApiOperation("删除员工")
    @PostMapping("/del")
    public  RespBean del(@MultiRequestBody User user){
        return userService.del(user);
    }
}
