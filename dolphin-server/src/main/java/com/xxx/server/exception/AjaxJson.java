package com.xxx.server.exception;

import java.util.LinkedHashMap;

/**
 * ajax请求后需要接受的JSON
 * 统一返回值类型,便于在页面上进行统一处理
 */
public class AjaxJson {

    private boolean success = true;    // 是否成功
    private String errorCode = "-1";   //错误代码
    private String msg = "操作成功";    // 提示信息


    public AjaxJson() {
    }

    /**
     * 出现异常后调用此方法封装异常
     *
     * @param e 异常
     */
    public AjaxJson(Throwable e) {

        this.success = false;
        this.errorCode = "-1";
        this.msg = e.getMessage();
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {//向json中添加属性，在js中访问，请调用data.msg
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
