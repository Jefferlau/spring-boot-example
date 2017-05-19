package com.jusfoun.service;

import com.jusfoun.config.TestConfiguration;
import com.jusfoun.dao.mapper.UserMapper;
import com.jusfoun.model.User;
import com.jusfoun.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * UserService Mock mapper 单元测试
 * Created by liutiyang on 2017/5/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfiguration.class})
public class UserServiceTests {
    @MockBean
    private UserMapper mapper;

    @Autowired
    private UserService userService;

    @Repeat(2)
    @Test
    public void testMockSelectById() {
        String userId = UUID.randomUUID().toString();
        User user = new User();
        user.setUserId(userId);
        given(this.mapper.selectByPrimaryKey(userId)).willReturn(user);

        User u = userService.selectByPrimaryKey(userId);
        assertThat(u.getUserId()).isEqualTo(user.getUserId());
        System.out.println(u);
    }

}
