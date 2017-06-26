package com.jusfoun.web.controller;

import com.jusfoun.service.impl.UserService;
import com.jusfoun.web.dto.UserDto;
import com.jusfoun.web.response.BaseResponse;
import com.jusfoun.web.service.UserManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liutiyang on 2017/5/18.
 */
@Api("用户相关接口")
@RestController
@RequestMapping("user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserManageService userManageService;

    @Autowired
    public UserController(UserManageService userManageService) {
        Assert.notNull(userManageService, "userManageService must not be null!");
        this.userManageService =  userManageService;
    }

    @ApiOperation(value = "RequestParam", notes = "RequestParam 接口示例，参数可以选择是否必填并设置默认值，默认必填，设置默认值后为非必填。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", paramType = "query", dataType = "String", defaultValue = "93201214-3b98-11e7-8b90-efc0f68ee6d7", required = true)
    })
    @GetMapping("/userInfo")
    public BaseResponse<UserDto> info(@RequestParam(value = "id") String id) {
        if (logger.isDebugEnabled()) {
            logger.debug("Query user by id: " + id);
        }

        BaseResponse<UserDto> baseResponse = new BaseResponse<>();
        return baseResponse.setData(userManageService.findById(id));
    }

    @ApiOperation(value = "ModelAttribute", notes = "ModelAttribute 接收单个参数。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "编号", paramType = "query", dataType = "String", defaultValue = "93201214-3b98-11e7-8b90-efc0f68ee6d7", required = true)
    })
    @GetMapping("/userInfoById")
    public BaseResponse<UserDto> infoById(@ModelAttribute UserDto userDto) {
        if (logger.isDebugEnabled()) {
            logger.debug("Query user by id: " + userDto.getUserId());
        }

        BaseResponse<UserDto> baseResponse = new BaseResponse<>();
        return baseResponse.setData(userManageService.findById(userDto.getUserId()));
    }

    @ApiOperation(value = "PathVariable", notes = "PathVariable 接口示例，参数无默认值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", paramType = "path", dataType = "String", required = true)
    })
    @GetMapping("/userInfo/{id}")
    public BaseResponse<UserDto> infoPath(@PathVariable("id")  String id) {
        return info(id);
    }

    @ApiOperation(value = "RequestBody", notes = "RequestBody 接口示例，参数无默认值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userDto", value = "用户", dataType = "UserDto")
    })
    @PostMapping("/userInfo")
    public BaseResponse<UserDto> saveUser(@Validated(UserDto.New.class) @RequestBody UserDto userDto) {

        BaseResponse<UserDto> baseResponse = new BaseResponse<>();
        return baseResponse.setData(userManageService.save(userDto));
    }

    @ApiOperation(value = "ModelAttribute", notes = "ModelAttribute 将数据绑定到对象。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "编号", paramType = "form", dataType = "string", required = true),
            @ApiImplicitParam(name = "realName", value = "姓名", paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "idCard", value = "身份证号码", paramType = "form", dataType = "string"),
    })
    @PutMapping("/userInfo")
    public BaseResponse<UserDto> updateUser(@Validated({UserDto.Existing.class, UserDto.New.class}) @ModelAttribute UserDto userDto) {
        BaseResponse<UserDto> baseResponse = new BaseResponse<>();
        return baseResponse.setData(userManageService.update(userDto));
    }
}
