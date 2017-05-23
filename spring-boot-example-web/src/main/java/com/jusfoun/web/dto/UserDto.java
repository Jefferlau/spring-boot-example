package com.jusfoun.web.dto;

import com.jusfoun.web.validation.IdCardValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by liutiyang on 2017/5/16.
 */
@ApiModel(value = "用户数据传输模型", description = "用于用户数据传输的模型对象")
public class UserDto implements Serializable {
    private static final long serialVersionUID = 4238297034619787615L;

    @ApiModelProperty(value = "编号", dataType = "string")
    @NotBlank(message = "-30003", groups = {Existing.class})
    private String userId;

    @ApiModelProperty(value = "姓名", dataType = "string")
    @NotBlank(message = "-30004", groups = {New.class})
    private String realName;

    @ApiModelProperty(value = "身份证号码", dataType = "string")
    @IdCardValid(message = "-30001", groups = {New.class})
    @NotBlank(message = "-30002", groups = {New.class})
    private String idCard;

    public String getUserId() {
        return userId;
    }

    public UserDto setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public UserDto setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public UserDto setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", realName='" + realName + '\'' +
                ", idCard=" + idCard +
                '}';
    }

    public interface Existing {}
    public interface New {}
}
