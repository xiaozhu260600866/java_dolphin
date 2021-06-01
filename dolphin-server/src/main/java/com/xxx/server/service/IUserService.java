package com.xxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-01
 */
public interface IUserService extends IService<User> {

    RespBean login(String username,
                   String password,
                   String code,
                   HttpServletRequest request);

    User getAdminByUserName(String username);

    List<Role> getRoles(Integer adminId);




    RespBean create(UserInfo userInfo, User user,UserRole userRole);

    RespBean edit(UserInfo userInfo, User user,UserRole userRole);

    RespBean del(User user);

    List<UserInfo> getStaffLists(Map params);

    List<UserInfo> getLists(Map params);
}
