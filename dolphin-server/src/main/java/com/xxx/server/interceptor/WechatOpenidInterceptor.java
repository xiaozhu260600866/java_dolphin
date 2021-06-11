package com.xxx.server.interceptor;

import com.xxx.server.utils.HttpURLConnectionDemo;
import com.xxx.server.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class WechatOpenidInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getParameter("code") ==null){
            String uri = request.getRequestURI();
            String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb7d00aa90f8d5211&redirect_uri="+ Utils.getUrl()+uri+"&response_type=code&scope=snsapi_userinfo&state=state#wechat_redirect";
            response.sendRedirect(redirectUrl);
        }else{
            String url    = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxb7d00aa90f8d5211&secret=6e5dda64cc12201c12381a650b0cd5da&code="+request.getParameter("code")+"&grant_type=authorization_code";
            HttpURLConnectionDemo httpURLConnectionDemo = new HttpURLConnectionDemo();
            Map map = httpURLConnectionDemo.doGet(url);
            String accessToken = (String)map.get("access_token");
            String openid = (String)map.get("openid");
            url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";
            map = httpURLConnectionDemo.doGet(url);
            request.setAttribute("userInfo",map);
            System.out.println(map);

        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
