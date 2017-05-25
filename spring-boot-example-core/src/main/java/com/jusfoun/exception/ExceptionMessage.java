package com.jusfoun.exception;

/**
 * 异常信息枚举
 * Created by liutiyang on 2017/5/4.
 */
public enum ExceptionMessage {
    ValidateIdCardFormatError(-30001, "身份证号码格式错误！"),
    ValidateIdCardEmpty(-30002, "身份证号码不能为空！"),
    IdEmpty(-30003, "编号不能为空！"),
    RealNameEmpty(-30004, "姓名不能为空！"),
    PasswordEmpty(-30005, "密码不能为空！");

    private int code;
    private String message;

    ExceptionMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }

    public static String getMessageByCode(int code) {
        for (ExceptionMessage message : ExceptionMessage.values()) {
            if (code == message.getCode()) {
                return message.getMessage();
            }
        }
        return "未知异常";
    }
}
