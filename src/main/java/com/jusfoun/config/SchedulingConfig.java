package com.jusfoun.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-02
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"com.jusfoun.task"})
public class SchedulingConfig {
}
