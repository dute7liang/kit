package com.scxx.base.web.tips;

import org.springframework.http.HttpStatus;

/**
 * 返回给前台的成功提示
 *
 * @author zl
 * @date 2019年8月28日14:06:20
 */
public class SuccessTip extends Tip {
	
	public SuccessTip(){
		super.success = true;
		super.status = HttpStatus.OK.value();
		super.msg = "操作成功";
	}
}
