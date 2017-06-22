package com.jusfoun.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liutiyang on 2017/5/16.
 */
@ApiModel(value = "基本响应模型", description = "请求响应时最基本的响应模型。")
public class BaseResponse<T> {

    @ApiModelProperty(value = "响应编码，非 HTTP 状态码。", dataType = "int")
    private int code;
    @ApiModelProperty(value = "响应消息", dataType = "string")
    private String message;
    @ApiModelProperty(value = "响应数据对象，不同接口返回对象可能不同。", dataType = "object")
    private T data;

    public BaseResponse() {
        this.setCode(0).setMessage("success");
    }

    public int getCode() {
        return code;
    }

    public BaseResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
