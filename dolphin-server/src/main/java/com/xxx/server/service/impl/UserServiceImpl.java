package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xxx.server.config.security.component.JwtTokenUtil;
import com.xxx.server.mapper.UserInfosMapper;
import com.xxx.server.mapper.UserMapper;
import com.xxx.server.mapper.RoleMapper;
import com.xxx.server.mapper.UserRoleMapper;
import com.xxx.server.pojo.*;
import com.xxx.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserInfosMapper userInfoMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private  String tokenHead;
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String encode = passwordEncoder.encode("123");
        System.out.println("endcode"+encode);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println(userDetails);
        if(null == userDetails ||!passwordEncoder.matches(password,userDetails.getPassword()) ){
            return RespBean.error("密码错误");
        }
        if(!userDetails.isEnabled()){
            return RespBean.error("帐号被禁用");
        }

        String token = jwtTokenUtil.generateToken(userDetails);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return RespBean.success("登录成功",tokenMap);
    }
    /*
    *@auth xiaozhu
    *@date 2021/5/20
    *description 根据username 搜索
    *params * @Param username:
    *return com.xxx.server.pojo.User
    */
    @Override
    public User getAdminByUserName(String username) {

        return userMapper.selectOne(new QueryWrapper<User>().eq("username",username).eq("enabled",true));
    }
    /*
    *@auth xiaozhu
    *@date 2021/5/20
    *description根据user_id 搜索所有role
    *params * @Param userId:
    *return java.util.List<com.xxx.server.pojo.Role>
    */
    @Override
    public List<Role> getRoles(Integer userId) {
        return roleMapper.getRoles(userId);
    }
    /*
    *@auth xiaozhu
    *@date 2021/5/20
    *description 获取所有会员列表
    *params * @Param params:
    *return java.util.List<com.xxx.server.pojo.User>
    */
    @Override
    public List<UserInfo> getLists(Map params) {

        return userInfoMapper.getLists(params);
    }

    /*
    *@auth xiaozhu
    *@date 2021/5/21
    *description 创建会员
    *params * @Param userInfo:
     * @Param user:
    *return com.xxx.server.pojo.RespBean
    */
    @Override
    @Transactional
    public RespBean create(UserInfo userInfo, User user,UserRole userRole) {
         user.setUsername(userInfo.getPhone());
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         userMapper.insert(user);
         userInfo.setUserId(user.getId());
         userInfoMapper.insert(userInfo);

        if (userRole != null && userRole.getRoleId() !=null) {
            userRole.setUserId(user.getId());
            userRoleMapper.insert(userRole);
        }


        return RespBean.success("创建成功");


    }

    @Override
    public RespBean edit(UserInfo userInfo, User user,UserRole userRole) {
        user.setUsername(userInfo.getPhone());
        if(user.getPassword() !=null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userMapper.updateById(user);
        userInfoMapper.updateById(userInfo);

        if (userRole != null &&  userRole.getRoleId() !=null) {
            userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id",user.getId()));
            userRole.setUserId(user.getId());
            userRoleMapper.insert(userRole);
        }
        return RespBean.success("修改成功");

    }

    @Override
    @Transactional
    public RespBean del(User user) {
       userMapper.deleteById(user.getId());
       userInfoMapper.delete(new QueryWrapper<UserInfo>().eq("user_id",user.getId()));
       userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id",user.getId()));
       return RespBean.success("删除成功");
    }

    /**
     * 员工列表
     * @param userInfo
     * @param shop
     * @param user
     * @return
     */
    @Override
    public List<UserInfo> getStaffLists(Map params) {
        return userInfoMapper.getStaffLists(params);
    }


}
