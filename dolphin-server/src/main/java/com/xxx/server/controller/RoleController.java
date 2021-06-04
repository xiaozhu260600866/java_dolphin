package com.xxx.server.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Role;
import com.xxx.server.service.IRoleService;
import com.xxx.server.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-01
 */

@RestController
@RequestMapping("/role")
@Validated
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @ApiOperation("取全部角色")
    @GetMapping("/lists")


    public Map getLists(@RequestParam(required = false) Map params){
        RespBean.startPage(params);
        List<Role> list = roleService.getAllRole(params);
        Map lists = RespBean.getLists(list);
        String[] roleMenus = {"user.lists.create","user.lists.edit"};
        System.out.println(Utils.getRoleMenu2(roleMenus));

        return lists;
    }

    @ApiOperation("创建角色")
    @PostMapping("/create")
    public RespBean createRole(@Valid  @RequestBody Role role){
        RespBean result = roleService.createRoleMenus(role);
        return result;
    }

    @ApiOperation("修改角色")
    @PostMapping("/edit")
    public RespBean editRole(@Valid @RequestBody Role role){
        RespBean result = roleService.editRoleMenus(role);
        return result;
    }
    @ApiOperation("删除角色")
    @PostMapping("/delete")
    public RespBean delRole(@RequestBody Role role){
        RespBean result = roleService.delRoleMenus(role);
        return result;
    }

    @ApiOperation("删除全部角色")
    @PostMapping("/deleteAll")
    public RespBean delRoleAll(@RequestBody Map params){
        String idstr = (String) params.get("idstr");
        String[] ids = idstr.split("\\|");
        System.out.println(Arrays.toString(ids));
        for (String id : ids) {
            Role role = new Role();
            role.setId(Integer.parseInt(id));
            roleService.delRoleMenus(role);
        }
        return RespBean.success("删除成功");
    }
}
