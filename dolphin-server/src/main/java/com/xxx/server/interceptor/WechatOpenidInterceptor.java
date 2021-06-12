package com.xxx.server.interceptor;

import com.xxx.server.exception.GlobalExceptionHandler;
import com.xxx.server.utils.HttpURLConnectionDemo;
import com.xxx.server.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Map;

public class WechatOpenidInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("params33333333333");
        HttpSession session = request.getSession();

        if(session.getAttribute("userInfo") ==null){
            if(request.getParameter("code") ==null){
                StringBuilder uri = new StringBuilder();
                uri.append(request.getRequestURI());
                uri.append("?"+Utils.getAllParams(request));
                String params =Utils.getAllParams(request);
                params = params.substring(0, params.indexOf("source")-1);
                logger.info("params"+params);
                session.setAttribute("params",params);
                session.setAttribute("source",request.getParameter("source"));
                String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb7d00aa90f8d5211&redirect_uri="+ Utils.getUrl()+uri.toString()+"&response_type=code&scope=snsapi_userinfo&state=state#wechat_redirect";
                response.sendRedirect(redirectUrl);
            }else{
                String params2 =Utils.getAllParams(request);
                logger.info("params2"+params2);
                String url    = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxb7d00aa90f8d5211&secret=6e5dda64cc12201c12381a650b0cd5da&code="+request.getParameter("code")+"&grant_type=authorization_code";
                HttpURLConnectionDemo httpURLConnectionDemo = new HttpURLConnectionDemo();
                Map map = httpURLConnectionDemo.doGet(url);
                String accessToken = (String)map.get("access_token");
                String openid = (String)map.get("openid");
                url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";
                map = httpURLConnectionDemo.doGet(url);
                session.setAttribute("userInfo",map);
                logger.info("page"+request.getSession().getAttribute("params"));
                logger.info("source"+request.getSession().getAttribute("source"));
                System.out.println(map);

            }
        }else{
            StringBuilder uri = new StringBuilder();
            uri.append(request.getRequestURI());
            uri.append("?"+Utils.getAllParams(request));
            String params =Utils.getAllParams(request);
            params = params.substring(0, params.indexOf("source")-1);
            logger.info("params"+params);
            session.setAttribute("params",params);
            session.setAttribute("source",request.getParameter("source"));
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
