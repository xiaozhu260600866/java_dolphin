package com.xxx.server.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.server.annotation.MultiRequestBody;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Role;
import com.xxx.server.pojo.Shop;
import com.xxx.server.service.IShopService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/shop/")
@Validated
public class ShopController {
    @Autowired
    private IShopService shopService;

    @ApiOperation("查看门店列表1")
    @GetMapping("/")
    public Map getLists(Shop shop){
        PageHelper.startPage(1, 15, true);
        List<Shop> list = shopService.list();
        PageInfo<Shop> pageInfo = new PageInfo<Shop>(list);
        Map lists = RespBean.getLists(pageInfo, list);
        return lists;
    }
    @ApiOperation("新增门店")
    @PostMapping("/create")
    public RespBean create(@Valid @MultiRequestBody Shop shop){
        return shopService.create(shop);
    }

    @ApiOperation("修改门店")
    @PostMapping("/edit")
    public  RespBean edit(@Valid @MultiRequestBody Shop shop){
        return  shopService.edit(shop);
    }
    @ApiOperation("删除门店")
    @PostMapping("del")
    public  RespBean del(@MultiRequestBody Shop shop){
        return  shopService.del(shop);
    }

}
