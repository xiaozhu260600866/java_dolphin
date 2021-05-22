package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.MenuRole;
import com.xxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-01
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {




    List<MenuRole> selectMenusByRoleId(Integer rid);
}
