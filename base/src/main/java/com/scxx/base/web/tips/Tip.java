package com.scxx.base.web.tips;

import lombok.Data;

/**
 * 返回给前台的提示（最终转化为json形式）
 *
 * @author zl
 * @Date 2019年8月28日14:05:59
 */
@Data
public abstract class Tip {

    protected int status;
    protected String msg;
    protected boolean success;

}
