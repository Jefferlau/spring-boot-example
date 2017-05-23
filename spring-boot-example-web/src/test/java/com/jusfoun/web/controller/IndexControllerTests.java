package com.jusfoun.web.controller;

import com.jusfoun.service.impl.UserService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liutiyang on 2017/5/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testIndex() throws Exception {

        ResultActions resultActions = this.mvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON_UTF8));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.code", is(0)))
                .andExpect(content().string("{\"code\":0,\"message\":\"success\",\"data\":null}"))
                .andDo(print());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(contentAsString)
                .containsPattern("^\\{")
                .containsPattern("\\}$")
                .containsPattern("^\\{.*\\}$");
    }

}
