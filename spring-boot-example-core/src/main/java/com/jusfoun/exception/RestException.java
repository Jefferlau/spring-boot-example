package com.jusfoun.exception;


/**
 * RESTful 接口统一异常
 * Created by liutiyang on 2017/3/13.
 */
public class RestException extends RuntimeException {
    private static final long serialVersionUID = -7757279597017929281L;

    private int errorCode;

    public RestException(int errorCode) {
        super(ExceptionMessage.getMessageByCode(errorCode));
        this.errorCode = errorCode;
    }

    public RestException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RestException(int errorCode, Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
    }

    public RestException(int errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
