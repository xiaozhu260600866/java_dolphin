package com.xxx.server.interceptor;

import com.xxx.server.utils.Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WechatOpenidInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getParameter("code") ==null){
            String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx40ebf20556177f80&redirect_uri="+ Utils.getUrl()+"&response_type=code&scope=snsapi_userinfo&state=state#wechat_redirect";
            response.sendRedirect(redirectUrl);
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
