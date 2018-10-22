package com.ga.security.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author:luqi
 * @description:  更具 用户 名（前台的） 查询数据库 获取到  真实的 用户信息 组装为 UserDetails 给 spring security 框架验证
 * @date:2018/10/11_10:10
 */
@Slf4j
@Component
public class MUserDetailService implements UserDetailsService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登陆用户名,{}",username);
        //判断用户的状态
        String password=passwordEncoder.encode("123456");
        log.info("数据库密码为,{}",password);
        User user=new User(username,password,true,true,
                true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
        return user;
    }
}
