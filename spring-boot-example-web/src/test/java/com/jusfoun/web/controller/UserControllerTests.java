package com.jusfoun.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jusfoun.model.User;
import com.jusfoun.service.impl.UserService;
import com.jusfoun.web.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 对 Controller 进行 Mock 测试。
 * Created by liutiyang on 2017/5/22.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mvc;

    private JacksonTester<User> userJacksonTester;
    private JacksonTester<UserDto> userDtoJacksonTester;

    @MockBean
    private UserService userService;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        JacksonTester.initFields(this, mapper);
    }

    @Test
    public void testGetUserInfo() throws Exception {

        String userId = UUID.randomUUID().toString();

        User user = new User();
        user.setUserId(userId);
        given(userService.selectByPrimaryKey(userId)).willReturn(user);

        ResultActions resultActions = this.mvc.perform(get("/userInfo")
                .param("id", userId)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("{\"code\":0,\"message\":\"success\",\"data\":" + userJacksonTester.write(user).getJson() + "}"))
                .andDo(print());

    }

    @Test
    public void testGetUserInfoById() throws Exception {

        String userId = UUID.randomUUID().toString();

        User user = new User();
        user.setUserId(userId);
        user.setUsername("jefferlau");
        given(userService.selectByPrimaryKey(userId)).willReturn(user);

        ResultActions resultActions = this.mvc.perform(get("/userInfoById")
                .param("userId", userId)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("{\"code\":0,\"message\":\"success\",\"data\":" + userJacksonTester.write(user).getJson() + "}"))
                .andDo(print());

    }

    @Test
    public void testGetUserInfoPathVariable() throws Exception {

        String userId = UUID.randomUUID().toString();

        User user = new User();
        user.setUserId(userId);
        user.setUsername("jefferlau");
        given(userService.selectByPrimaryKey(userId)).willReturn(user);

        ResultActions resultActions = this.mvc.perform(get("/userInfo/" + userId)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.userId", is(userId)))
                .andExpect(content().string("{\"code\":0,\"message\":\"success\",\"data\":" + userJacksonTester.write(user).getJson() + "}"))
                .andDo(print());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(contentAsString).contains(userId);
    }

    @Test
    public void testSaveUser() throws Exception {
        String idCard = "371082199006232567";

        UserDto userDto = new UserDto()
                .setRealName("Jeffer Lau")
                .setIdCard(idCard);

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setUsername("jefferlau");

        given(userService.insertSelective(user)).willReturn(1);

        String requestBody = this.userDtoJacksonTester.write(userDto).getJson();

        System.out.println(requestBody);

        ResultActions resultActions = this.mvc.perform(post("/userInfo")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.idCard", is(idCard)))
                .andExpect(jsonPath("$.data.userId", notNullValue()))
                .andDo(print());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(contentAsString)
                .containsPattern("^\\{")
                .containsPattern("\\}$")
                .containsPattern("^\\{.*\\}$");
    }

    @Test
    public void testEditUser() throws Exception {

        User user = new User();
        user.setUsername("jefferlau");
        user.setRealName("Jeffer Lau");
        user.setIdCard("371082199006232567");

        given(userService.updateByPrimaryKeySelective(user)).willReturn(1);

        ResultActions resultActions = this.mvc.perform(put("/userInfo")
                .param("userId", UUID.randomUUID().toString())
                .param("realName", user.getRealName())
                .param("idCard", user.getIdCard())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.userId", notNullValue()))
                .andExpect(jsonPath("$.data.idCard", is(user.getIdCard())))
                .andDo(print());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(contentAsString)
                .containsPattern("^\\{")
                .containsPattern("\\}$")
                .containsPattern("^\\{.*\\}$");
    }

}
