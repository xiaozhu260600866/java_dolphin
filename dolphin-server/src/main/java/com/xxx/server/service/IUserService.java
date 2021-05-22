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


    List<UserInfo> getLists(UserInfo params, Shop shop);

    RespBean create(UserInfo userInfo, User user);

    RespBean edit(UserInfo userInfo, User user);

    RespBean del(User user);
}
