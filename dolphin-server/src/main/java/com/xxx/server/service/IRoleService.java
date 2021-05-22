package com.xxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-01
 */
public interface IRoleService extends IService<Role> {

    List<Role> getAllRole(Role role);

    RespBean createRoleMenus(Role role);

    RespBean editRoleMenus(Role role);

    RespBean delRoleMenus(Role role);
}
