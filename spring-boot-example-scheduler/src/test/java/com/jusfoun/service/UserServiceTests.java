package com.jusfoun.service;

import com.jusfoun.model.User;
import com.jusfoun.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * UserService 单元测试
 * Created by liutiyang on 2017/5/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Repeat(2)
    @Test
    public void testSelectById() {
        String userId = "93201214-3b98-11e7-8b90-efc0f68ee6d7";

        User user = userService.selectByPrimaryKey(userId);
        assertThat(user).isNotNull();
        assertThat(user.getUserId()).isEqualTo(userId);
        System.out.println(user);
    }
}
