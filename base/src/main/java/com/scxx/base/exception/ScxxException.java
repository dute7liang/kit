package com.scxx.base.exception;

/**
 * @author: zl
 * @Date: 2019-8-28 14:13
 */
public class ScxxException extends RuntimeException {

	private Integer code;

	private String message;

	public ScxxException(ServiceExceptionEnum serviceExceptionEnum) {
		this.code = serviceExceptionEnum.getCode();
		this.message = serviceExceptionEnum.getMessage();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
