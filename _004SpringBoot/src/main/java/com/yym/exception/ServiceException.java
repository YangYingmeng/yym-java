package com.yym.exception;

/**
 * @Description: 服务层异常
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-18 23:42
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String msg) {
        super(msg);
    }
}
