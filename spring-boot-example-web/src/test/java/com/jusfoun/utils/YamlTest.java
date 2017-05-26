package com.jusfoun.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * Create on 2017-05-26.
 *
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class YamlTest {

    @Test
    public  void readProperties() {
        YamlProp yamlProp = new YamlProp();
        yamlProp.readProperties();
    }

}
