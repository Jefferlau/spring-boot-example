package com.jusfoun.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 非 Spring 容器管理的类，通过 Spring 上下文读取配置文件。
 * Create on 2017-05-26.
 *
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
public class YamlProp {

    public void readProperties() {
        // 读取配置文件
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        Environment environment = applicationContext.getEnvironment();
        String host = environment.getProperty("spring.mail.host");
        System.out.println(host);
    }
}
