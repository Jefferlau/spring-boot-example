package com.jusfoun.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 普通类调用 Spring bean 对象。
 * 注意使用时以下两种方式任选其一：
 * 1、此类加上 @Component 注解并需要放到可以被被扫描的目录。
 * 2、在任意 @Configuration 定义 @Bean public SpringUtil springUtil(){return new SpringUtil();}
 * 3、在任意 @Configuration 类上注解 @Import(value={SpringUtil.class})
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-02
 */
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;
    private static Logger logger = LoggerFactory.getLogger(SpringUtil.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null){
            SpringUtil.applicationContext  = applicationContext;
        }
        if (logger.isInfoEnabled()) {
            logger.info("普通类可以通过调用 SpringUtils.getAppContext() 获取 applicationContext 对象, applicationContext = {}"
                    , SpringUtil.applicationContext);
        }
    }

    /**
     * 获取 applicationContext 对象。
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过 name 获取 Bean。
     * @param name Bean name
     * @return Bean 实例
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过 class 获取 Bean。
     * @param clazz Bean clazz
     * @param <T> Bean
     * @return Bean 实例
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过 name, 以及 Clazz 返回指定的 Bean。
     * @param name Bean name
     * @param clazz Bean clazz
     * @param <T> Bean
     * @return Bean 实例
     */
    public static <T> T getBean(String name, Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
