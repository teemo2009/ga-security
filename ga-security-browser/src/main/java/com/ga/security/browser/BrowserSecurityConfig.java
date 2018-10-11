package com.ga.security.browser;

import com.ga.security.browser.authentication.GaAuthenticationFailureHandler;
import com.ga.security.browser.authentication.GaAuthenticationSuccessHandler;
import com.ga.security.core.properties.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

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
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                 //配置成功登陆后的操作，框架 没有默认  会报 http 302错误
                .successHandler(gaAuthenticationSuccessHandler)
                .failureHandler(gaAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                //当匹配到登陆界面时 不需要认证
                .antMatchers("/authentication/require","/error",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
        /**http basic登陆*/
       /* http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();*/
    }


    /**
     * 添加一个 不需要对密码做任何处理的密码编码器,在spring security 5.0后 需要手动设置
     */
  /*  @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }*/


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
