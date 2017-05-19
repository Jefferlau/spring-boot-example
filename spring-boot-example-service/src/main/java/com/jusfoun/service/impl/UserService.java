package com.jusfoun.service.impl;

import com.jusfoun.dao.mapper.UserMapper;
import com.jusfoun.model.User;
import com.jusfoun.model.UserExample;
import com.jusfoun.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 用户 Service
 * Created by liutiyang on 2017/5/18.
 */
@Service
public class UserService extends BaseService<UserMapper, User, UserExample> {

}
