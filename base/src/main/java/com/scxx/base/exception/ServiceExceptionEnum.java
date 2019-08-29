package com.scxx.base.exception;

/**
 * 抽象接口
 *
 * @author zl
 * @date 2019年8月28日14:15:57
 */
public interface ServiceExceptionEnum {

    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
