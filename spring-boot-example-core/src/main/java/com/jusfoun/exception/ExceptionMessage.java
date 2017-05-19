package com.jusfoun.exception;

/**
 * 异常信息枚举
 * Created by liutiyang on 2017/5/4.
 */
public enum ExceptionMessage {
    ValidateIdCardFormatError(-30001, "身份证号码格式错误！"),
    ValidateIdCardEmpty(-30002, "身份证号码不能为空！"),
    TimestampInvalid(-30003, "编号不能为空！"),
    ParseDataError(-30004, "姓名不能为空！"),
    GenerateDataError(-30005, "返回数据时序列化失败"),
    IllegalBuffer(-30006, "解密后得到的 buffer 非法"),
    EncryptBlank(-30007, "encrypt 不能为空！"),
    SignatureBlank(-30008, "signature 不能为空！"),
    TimestampBlank(-30009, "timestamp 不能为空！"),
    NonceBlank(-30010, "nonce 不能为空！");

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
