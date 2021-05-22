package com.xxx.server.controller;


import com.xxx.server.pojo.Menu;
import com.xxx.server.service.IUserService;
import com.xxx.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-01
 */
@RestController
@RequestMapping("/system/config")
public class MenuController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IUserService adminService;
}
