package com.jusfoun.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "异常时的响应模型", description = "当请求发生异常时，返回的响应模型")
public class ErrorResponse {
    @ApiModelProperty(value = "错误码，非 HTTP 响应码。", dataType = "int")
    private int code;
    @ApiModelProperty(value = "错误消息", dataType = "string")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
