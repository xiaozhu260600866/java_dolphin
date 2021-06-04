package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xxx.server.mapper.MenuMapper;
import com.xxx.server.mapper.MenuRoleMapper;
import com.xxx.server.mapper.RoleMapper;
import com.xxx.server.pojo.Menu;
import com.xxx.server.pojo.MenuRole;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Role;
import com.xxx.server.service.IRoleService;
import com.xxx.server.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Override
    public List<Role> getAllRole(Map params) {
        List<Role> allRole = roleMapper.getAllRole(params);
        allRole.forEach(r->{
            List<String> roleMenuList = new ArrayList<>();
            List<MenuRole> roleMenus = menuRoleMapper.selectMenusByRoleId(r.getId());
            System.out.println(roleMenus.size());
            System.out.println(roleMenus);
            roleMenus.forEach(roleMenu->{
                roleMenuList.add(roleMenu.getMenu().getComponent());
            });
            r.setRole_menus((String[]) roleMenuList.toArray(new String[0]));
        });
        return allRole;
    }

//    新建角色

    @Override
    @Transactional
    public RespBean createRoleMenus(Role role) {
        role.setMenu_str(String.join(",",role.getRole_menus()));
        int insert = roleMapper.insert(role);
        Integer roleId = role.getId();
        List<Integer> menuIds = new ArrayList<>();
        HashSet<String> roleMenu2 = Utils.getRoleMenu2(role.getRole_menus());
        for (String role_menu : roleMenu2) {
            Menu menu = new Menu();
            menu.setComponent(role_menu);
            String url = "/"+role_menu.replace(".", "/");
            System.out.println(url);
            menu.setUrl(url);
            menuMapper.insert(menu);
            menuIds.add(menu.getId());
        }
        menuIds.forEach(menuid->{
            MenuRole menuRole = new MenuRole();
            menuRole.setMid(menuid);
            menuRole.setRid(roleId);
            menuRoleMapper.insert(menuRole);
        });
        return RespBean.success("添加成功");
    }

    @Override
    @Transactional
    public RespBean editRoleMenus(Role role) {
        role.setMenu_str(String.join(",",role.getRole_menus()));
        List<MenuRole> roleMenus = menuRoleMapper.selectMenusByRoleId(role.getId());
        System.out.println("role_menus");
        System.out.println(roleMenus.size());
        roleMenus.forEach(roleMenu->{
             menuMapper.delete(new QueryWrapper<Menu>().eq("id",roleMenu.getMenu().getId()));
        });
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",role.getId()));

        //把关联的删除

        roleMapper.updateById(role);
        List<Integer> menuIds = new ArrayList<>();
        HashSet<String> roleMenu2 = Utils.getRoleMenu2(role.getRole_menus());
        for (String role_menu : roleMenu2) {
            Menu menu = new Menu();
            menu.setComponent(role_menu);
            String url = "/"+role_menu.replace(".", "/");

            menu.setUrl(url);
            menuMapper.insert(menu);
            menuIds.add(menu.getId());
        }
        menuIds.forEach(menuid->{
            MenuRole menuRole = new MenuRole();
            menuRole.setMid(menuid);
            menuRole.setRid(role.getId());
            menuRoleMapper.insert(menuRole);
        });
        return RespBean.success("修改成功");

    }
    /*
    *@auth xiaozhu
    *@date 2021/5/19
    *description 删除角色
    *params * @Param role:
    *return com.xxx.server.pojo.RespBean
    */
    @Override
    @Transactional
    public RespBean delRoleMenus(Role role) {
        List<MenuRole> roleMenus = menuRoleMapper.selectMenusByRoleId(role.getId());
        System.out.println("role_menus");
        System.out.println(roleMenus.size());
        roleMenus.forEach(roleMenu->{
            menuMapper.delete(new QueryWrapper<Menu>().eq("id",roleMenu.getMenu().getId()));
        });
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",role.getId()));
        roleMapper.deleteById(role.getId());
        return RespBean.success("删除成功");
    }


}
