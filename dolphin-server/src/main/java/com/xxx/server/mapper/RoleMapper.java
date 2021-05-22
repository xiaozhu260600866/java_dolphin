package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-01
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoles(Integer userId);

    List<Role> getAllRole(Role role);


}
