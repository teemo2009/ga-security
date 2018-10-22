package com.ga.security.core.validate.code;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:luqi
 * @description: 一次性拦截器
 * @date:2018/10/22_11:29
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Resource
    private AuthenticationFailureHandler  authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.pathEquals("/authentication/form",httpServletRequest.getRequestURI()) &&
                httpServletRequest.getMethod().equalsIgnoreCase("post")){
            try{
                validate(new ServletWebRequest(httpServletRequest));
            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }

    private void validate(ServletWebRequest servletWebRequest) {
    }
}
