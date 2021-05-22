package com.xxx.server.exception;

import com.xxx.server.pojo.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 全局异常捕获类
 */
// 过此注解声明此类为一个全局异常处理类
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespBean handleException(HttpServletRequest request, Exception e) {
        System.out.println("a");
        logger.error("---------------------- handleException start ---------------------- ");
        logger.error("url: [{}]", request.getRequestURL().toString());
        logger.error(e.getMessage(), e);
        logger.error("---------------------- handleException end ---------------------- ");
        //判断
        if(e.getMessage().indexOf("alidation") > 0){
            String eMessage = e.getMessage().substring(e.getMessage().indexOf("default message"));
            String[] defaultMessages = eMessage.split("default message");

            return RespBean.error(defaultMessages[defaultMessages.length-1]);
        }else{
            return RespBean.error(e.getMessage());
        }


    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public AjaxJson handleServiceException(HttpServletRequest request, ServiceException e) {
        System.out.println("b");
        logger.error("---------------------- handleServiceException start ---------------------- ");
        logger.error("url: [{}]", request.getRequestURL().toString());
        logger.error(e.getMessage(), e);
        logger.error("---------------------- handleServiceException end ---------------------- ");
        return new AjaxJson(e);
    }
}