package com.jusfoun;

import com.jusfoun.utils.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jusfoun.config"})
@ServletComponentScan(basePackages = {"com.jusfoun.components"})
public class Starter {

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}

    @Bean
    public SpringUtil springUtil(){return new SpringUtil();}
}
