package com.ga.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.security.core.properties.LoginType;
import com.ga.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author:luqi
 * @description: 失败的处理
 * @date:2018/10/11_13:48
 */
@Slf4j
@Component("gaAuthenticationFailureHandler")
public class GaAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Resource
    ObjectMapper objectMapper;

    @Resource
    SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        log.info("登陆失败");
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(exception));
        }else{
            super.onAuthenticationFailure(request,response,exception);
        }

    }
}
