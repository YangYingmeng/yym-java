package com.yym.security.exception;

import javax.naming.AuthenticationException;

/**
 * @Description: token过期异常
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-11 14:35
 */
public class TokenExpiredException extends AuthenticationException {

    public TokenExpiredException() {
        super("Token is expired");
    }

}
