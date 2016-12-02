package com.jusfoun.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理。
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-02
 */
//@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public void defaultErrorHandler(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();

        /*
        * 返回json数据或者String数据：
        * 那么需要在方法上加上注解：@ResponseBody
        * 添加return即可。
        */

       /*
        * 返回视图：
        * 定义一个ModelAndView即可，
        * 然后return;
        * 定义视图文件(比如：error.html,error.ftl);
        */
    }
}
