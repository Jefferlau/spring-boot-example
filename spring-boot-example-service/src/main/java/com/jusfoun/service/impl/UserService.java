package com.jusfoun.service.impl;

import com.jusfoun.dao.mapper.UserMapper;
import com.jusfoun.model.User;
import com.jusfoun.model.UserExample;
import com.jusfoun.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 Service
 * Created by liutiyang on 2017/5/18.
 */
@Service
public class UserService extends BaseService<UserMapper, User, UserExample> {

    public User findByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> userList = mapper.selectByExample(example);
        User user = null;
        if (userList.size() > 0) {
            user = userList.get(0);
        }
        return user;
    }
}
