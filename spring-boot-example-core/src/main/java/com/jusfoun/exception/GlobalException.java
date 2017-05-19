package com.jusfoun.exception;

/**
 * MybatisPlus 异常类
 * Created by liutiyang on 2017/5/18.
 */
public class GlobalException extends RuntimeException {

	private static final long serialVersionUID = -6269331030718704234L;

	public GlobalException(String message) {
		super(message);
	}

	public GlobalException(Throwable throwable) {
		super(throwable);
	}

	public GlobalException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
