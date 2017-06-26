package com.jusfoun.web.service;

import com.jusfoun.model.User;
import com.jusfoun.service.impl.UserService;
import com.jusfoun.web.dto.UserDto;
import com.jusfoun.web.mapstruct.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

/**
 * Create on 2017-06-26.
 *
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
@Service
public class UserManageService {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserManageService(UserService userService, UserMapper userMapper) {
        Assert.notNull(userService, "UserService must not be null!");
        Assert.notNull(userMapper, "userMapper must not be null!");
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public UserDto findById(String id) {
        // System.out.println(id);
        return userMapper.toUserDto(userService.selectByPrimaryKey(id));
    }

    public UserDto save(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setUserId(UUID.randomUUID().toString());

        userService.insertSelective(user);
        return userMapper.toUserDto(user);
    }

    public UserDto update(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        userService.updateByPrimaryKeySelective(user);
        return userMapper.toUserDto(user);
    }
}
