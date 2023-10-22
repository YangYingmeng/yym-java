package com.yym.security.exception;

import javax.naming.AuthenticationException;

/**
 * @Description: 非法异常
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-11 14:34
 */
public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException() {
        super("Authentication token is invalid");
    }

}
