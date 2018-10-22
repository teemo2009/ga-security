package com.ga.security.app;

import com.ga.security.app.authentication.GaAuthenticationFailureHandler;
import com.ga.security.app.authentication.GaAuthenticationSuccessHandler;
import com.ga.security.core.properties.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    GaAuthenticationSuccessHandler gaAuthenticationSuccessHandler;

    @Resource
    GaAuthenticationFailureHandler gaAuthenticationFailureHandler;

    @Resource
    SecurityProperties securityProperties;

    /**
     * @author:luqi
     * @description: 设置默认为所有请求为表单登陆验证
     * @date:2018/10/11_9:02
     * @param:
     * @return:
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         *  自定义登陆界面 时候 注意放置在 static 目录下
         * */
        /**表单登陆**/
        http.formLogin().permitAll()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                 //配置成功登陆后的操作，框架 没有默认  会报 http 302错误
                .successHandler(gaAuthenticationSuccessHandler)
                .failureHandler(gaAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }





}
