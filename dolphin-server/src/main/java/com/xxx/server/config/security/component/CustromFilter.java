package com.xxx.server.config.security.component;


import com.xxx.server.pojo.Menu;
import com.xxx.server.pojo.Role;
import com.xxx.server.service.IMenuService;
import com.xxx.server.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//根据请求url请求所需要的角色
@Component
public class CustromFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IMenuService menuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请示的url
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //把问号后台的参数去掉
        System.out.println(requestUrl);
        if(requestUrl.contains("?")){
            requestUrl =  requestUrl.substring(0,requestUrl.indexOf("?"));
        }

        System.out.println(requestUrl);
        System.out.println("user:"+Utils.getUser());
        //判断如果是管理员角色的；
       if(null != Utils.getUser() && Utils.getUser().getRole() == 1){
           return SecurityConfig.createList("ROLE_ADMIN");
       }else{
           //        没pi配的url默认登录可访问
           System.out.println("没pi配的url默认登录可访问");
           List<Menu> menus = menuService.getMenusWithRole();
           if(menus !=null){
               for (Menu menu : menus) {
                   //判断请求url与菜单url是否一致
                   System.out.println(menu);
                   if(null != menu.getUrl() && antPathMatcher.match(menu.getUrl(),requestUrl)){
                       String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                       System.out.println("str");
                       System.out.println(Arrays.toString(str));
                       return SecurityConfig.createList(str);
                   }
               }
           }
           return SecurityConfig.createList("ROLE_NOAUTH");
       }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
