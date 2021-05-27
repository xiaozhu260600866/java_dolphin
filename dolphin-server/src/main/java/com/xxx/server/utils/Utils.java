package com.xxx.server.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxx.server.pojo.User;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

    /**
     * 用于全局图片格式化的
     */
    public static String getCoverString(String jsonString,String upurl){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = Utils.getUrl();
        List lists = JSON.parseArray(jsonString);
         List<String> res = new ArrayList<>();
        for (Object list : lists) {
            Map<String, Object> field = (Map<String, Object>) list;
            if(field.get("response") !=null){
                Map<String, Object> response = (Map<String, Object>) field.get("response");
                Map<String, Object> obj = (Map<String, Object>) response.get("obj");
                res.add(url+"/static/images/"+upurl+"/"+(String) obj.get("filename"));
            }else{
                res.add((String) field.get("url"));
            }
        }
        return StringUtils.join(res,",");
    }

    /*
    *后台url
    * getServiceUrl
    * */
    public static String getUrl(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        if(request.getServerName().contains("localhost")){
            return  "http://" + request.getServerName() + ":" + request.getServerPort();
        }else{
            return  "https://" + request.getServerName() + ":" + request.getServerPort();
        }
    }

    /**
     * 格式化vue 格式 label value
     */
    public static List<Map<String,Object>> getLabel(List<?> lists,String labelName){
        List<Map<String,Object>> resultList = new ArrayList<>();
        for (Object list : lists) {
            Map<String,Object> resultMap = new JSONObject();
            JSONObject json = (JSONObject) JSON.toJSON(list);
            resultMap.put("label",(String)json.get(labelName));
            resultMap.put("id",(Integer)json.get("id"));
            resultMap.put("value",(Integer)json.get("id"));
            resultList.add(resultMap);
        }
        return resultList;


    }
}
