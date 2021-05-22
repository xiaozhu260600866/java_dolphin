package com.xxx.server.utils;

import com.xxx.server.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
    /*
    *@auth xiaozhu
    *@date 2021/5/19
    *description 获取user对象
    *params 
    *return com.xxx.server.pojo.User        
    */
    public static User  getUser(){
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
    /*
    *@auth xiaozhu
    *@date 2021/5/19
    *description  设置跨域
    *params * @Param response:
    *return javax.servlet.http.HttpServletResponse
    */
    public  static HttpServletResponse setAllowOrigin(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        return response;
    }
   /*
   *@auth xiaozhu
   *@date 2021/5/19
   *description  用于角色表的
   *params * @Param getRoleMenu2:
   *return java.util.HashSet<java.lang.String>
   */
    public static HashSet<String> getRoleMenu2(String[] getRoleMenu2 ){
        HashSet<String> res = new HashSet<String>();;
        for (String menu: getRoleMenu2) {
            String[] arr = menu.split("\\.");
            if(arr.length == 2){
                res.add(arr[0]);
            }
            if(arr.length == 3){
                res.add(arr[0]);
                res.add(arr[0]+"."+arr[1]);

            }
            res.add(menu);
        }
        return res;
    }
}
