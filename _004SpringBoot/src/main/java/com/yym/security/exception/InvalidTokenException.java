package com.yym.security.exception;

import javax.naming.AuthenticationException;

/**
 * @Description: 非法token异常
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-19 19:44
 */
public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException() {
        super("Authentication token is invalid");
    }
}
