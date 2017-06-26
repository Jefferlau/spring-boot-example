package com.jusfoun.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.jusfoun.web.components.interceptor.MyInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-02
 */
@Configuration
@ComponentScan(basePackages = {"com.jusfoun.web.controller", "com.jusfoun.exception"})
@ServletComponentScan(basePackages = {"com.jusfoun.web.components"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

  /*  @Bean
    public FilterRegistrationBean myFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        MyFilter myFilter = new MyFilter();
        registrationBean.setFilter(myFilter);
        registrationBean.setOrder(Integer.MAX_VALUE);
        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean myFilter1RegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        MyFilter1 myFilter = new MyFilter1();
        registrationBean.setFilter(myFilter);
        registrationBean.setOrder(Integer.MAX_VALUE - 1);
        return registrationBean;
    }*/

    @Bean
    public MappingJackson2HttpMessageConverter messageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        // null 值转成 ""
        /*mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException {
                jg.writeString("");
            }
        });*/
        // key 首字母大写
        /*mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {
            private static final long serialVersionUID = -9040447651806412079L;
            // 序列化时调用
            @Override
            public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                Class<?> declaringClass = method.getDeclaringClass();
                String packageName = declaringClass.getPackage().getName();
                String className = declaringClass.getName();
                System.out.println(packageName);
                System.out.println(className);
                return super.nameForGetterMethod(config, method, StringUtils.capitalize(defaultName));
            }
            // 反序列化时调用
            @Override
            public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                return super.nameForSetterMethod(config, method, StringUtils.uncapitalize(defaultName));
            }
        });*/

        converter.setObjectMapper(mapper);
        return converter;
    }
}
