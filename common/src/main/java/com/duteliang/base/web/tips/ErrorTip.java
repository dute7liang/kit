package com.duteliang.base.web.tips;

/**
 * 返回给前台的错误提示
 *
 * @author zl
 * @date 2019年8月28日14:07:19
 */
public class ErrorTip extends Tip {

    public ErrorTip(int status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
        this.success = false;
    }


    public ErrorTip(String msg){
        this.msg = msg;
        this.success = false;
    }
}
