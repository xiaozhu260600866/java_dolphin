package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xxx.server.mapper.UserInfosMapper;
import com.xxx.server.pojo.UserInfo;
import com.xxx.server.service.IUserInfosService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-05-19
 */
@Service
public class UserInfosServiceImpl extends ServiceImpl<UserInfosMapper, UserInfo> implements IUserInfosService {

}
