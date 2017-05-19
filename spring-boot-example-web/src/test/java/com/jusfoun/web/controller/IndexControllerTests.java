package com.jusfoun.web.controller;

import com.jusfoun.config.DataSourceConfig;
import com.jusfoun.config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liutiyang on 2017/5/18.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(IndexController.class)
@ImportAutoConfiguration({WebConfig.class, DataSourceConfig.class})
public class IndexControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testIndex() throws Exception {

        ResultActions resultActions = this.mvc.perform(get("/")
//                .param("method", "company.list.get")
                .accept(MediaType.APPLICATION_JSON_UTF8));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }

}
