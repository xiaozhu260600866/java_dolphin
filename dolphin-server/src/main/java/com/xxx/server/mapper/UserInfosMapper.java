package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.Shop;
import com.xxx.server.pojo.User;
import com.xxx.server.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-19
 */
public interface UserInfosMapper extends BaseMapper<UserInfo> {
    List<UserInfo> getLists(@Param("userInfo") UserInfo userInfo, @Param("shop") Shop shop, @Param("user") User user);


    List<UserInfo> getStaffLists(@Param("userInfo") UserInfo userInfo, @Param("shop") Shop shop, @Param("user")  User user);
}
