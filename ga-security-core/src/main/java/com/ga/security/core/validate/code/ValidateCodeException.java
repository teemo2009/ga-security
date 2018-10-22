package com.ga.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;
/**
 * @author:luqi
 * @description: 图片验证码登陆 异常
 * @date:2018/10/22_11:34
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
