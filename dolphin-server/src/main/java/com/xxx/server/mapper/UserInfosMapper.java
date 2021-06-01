package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.Shop;
import com.xxx.server.pojo.User;
import com.xxx.server.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-19
 */
public interface UserInfosMapper extends BaseMapper<UserInfo> {

    List<UserInfo> getStaffLists(Map params);

    List<UserInfo> getLists(Map params);
}
