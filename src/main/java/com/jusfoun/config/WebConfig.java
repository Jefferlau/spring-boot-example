package com.jusfoun.config;

import com.jusfoun.components.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-02
 */
@Configuration
@ComponentScan(basePackages = {"com.jusfoun.controller"})
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
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

}
