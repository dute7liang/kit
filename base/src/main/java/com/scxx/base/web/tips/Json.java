package com.scxx.base.web.tips;

import lombok.Data;

/**
 * @author: zl
 * @Date: 2019-8-28 13:57
 */
@Data
public class Json extends Tip{

	private Object t;

	public Json(){}

	public Json(String msg){
		super.msg = msg;
	}

	public Json(boolean success,String msg){
		super.success = success;
		super.msg = msg;
	}



}
