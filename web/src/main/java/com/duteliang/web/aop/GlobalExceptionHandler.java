package com.duteliang.web.aop;

import com.scxx.base.exception.ScxxException;
import com.scxx.base.exception.ScxxExceptionEnum;
import com.scxx.base.support.HttpKit;
import com.scxx.base.web.tips.ErrorTip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author zl
 * @date 2019年8月28日15:02:33
 */
@ControllerAdvice
@Order(-1)
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(ScxxException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(ScxxException e) {
        HttpKit.getRequest().setAttribute("tip", e.getMessage());
        log.error("业务异常:", e);
        return new ErrorTip(e.getCode(), e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(RuntimeException e) {
        HttpKit.getRequest().setAttribute("tip", "服务器未知运行时异常");
        log.error("运行时异常:", e);
        return new ErrorTip(ScxxExceptionEnum.REQUEST_NULL.getCode(), ScxxExceptionEnum.REQUEST_NULL.getMessage());
    }

    /**
     * 拦截Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(Exception e) {
        HttpKit.getRequest().setAttribute("tip", "服务器未知运行时异常");
        log.error("Exception异常:", e);
        return new ErrorTip(ScxxExceptionEnum.REQUEST_NULL.getCode(), ScxxExceptionEnum.REQUEST_NULL.getMessage());
    }


}
