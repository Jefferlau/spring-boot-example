package com.jusfoun.config;

import com.jusfoun.security.CustomUserDetailsService;
import com.jusfoun.security.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.DefaultUserApprovalHandler;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/bootstrap/**",
                "/dist/**",
                "/libs/**",
                "/**/*.js",
                "/**/*.css",
                "/plugins/**",
                "/files/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //允许所有用户访问”/”和”/home”
        http.authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                //.antMatchers("/index", "/home", "/user/**").permitAll()
                //.antMatchers("/admin/login").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and()

                .formLogin()
                //指定登录页是”/login”
                .loginPage("/login").loginProcessingUrl("/login.do").defaultSuccessUrl("/user/info")
                .failureUrl("/login?error")
                .permitAll()

                //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .successHandler(loginSuccessHandler())

                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //退出登录后的默认网址是”/”
                .logoutSuccessUrl("/")
                .permitAll()
                .invalidateHttpSession(true)
                .and()

                //登录后记住用户，下次自动登录
                //数据库中必须存在名为persistent_logins的表
                //建表语句见 schema
                .rememberMe()
                // .tokenValiditySeconds(1209600)
                .tokenValiditySeconds(31536000)
                //指定记住登录信息所使用的数据源
                .tokenRepository(tokenRepository());
    }


    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //指定密码加密所使用的加密器为passwordEncoder()
        //需要将密码加密后写入数据库
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());

        //不删除凭据，以便记住用户
        auth.eraseCredentials(false);
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Configure spring security's authenticationManager with custom
        // user details service
        auth.userDetailsService(this.customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }*/

    @Override
    @Bean // share AuthenticationManager for web and oauth
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public JdbcTokenRepositoryImpl tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);

        return jdbcTokenRepository;
    }
/*
    *//**
     * 使用 JdbcTokenStore, 将 token 信息存放数据库
     * @return
     *//*
    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    *//**
     * TokenServices 需要注入 TokenStore
     * @return
     *//*
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        // 如果允许刷新token 请将supportRefreshToken 的值设置为true, 默认为不允许
        tokenServices.setSupportRefreshToken(true);
        return tokenServices;
    }

    @Bean
    public OAuth2AuthenticationEntryPoint oauth2AuthenticationEntryPoint() {
        return new OAuth2AuthenticationEntryPoint();
    }

    @Bean
    public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }

    *//**
     * 用户授权确认
     * @return
     *//*
    @Bean
    public DefaultUserApprovalHandler oauthUserApprovalHandler() {
        return new DefaultUserApprovalHandler();
    }

    *//**
     * ClientDetailsService 配置, 使用 JdbcClientDetailsService, 也需要提供 dataSource
     * @return
     *//*
    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    *//**
     * ClientDetailsUserDetailsService 配置, 该类实现了 Spring security 中 UserDetailsService 接口
     * @return
     *//*
    @Bean
    public ClientDetailsUserDetailsService clientDetailsUserDetailsService() {
        return new ClientDetailsUserDetailsService(clientDetailsService());
    }

    @Configuration
    class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenServices(tokenServices())
                    .userApprovalHandler(oauthUserApprovalHandler())
                    .setClientDetailsService(clientDetailsService());
            super.configure(endpoints);
        }
    }
    */
}
