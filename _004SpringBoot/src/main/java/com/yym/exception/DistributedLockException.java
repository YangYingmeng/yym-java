package com.yym.exception;

/**
 * @Description: 分布式锁异常
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-12 11:21
 */
public class DistributedLockException extends RuntimeException {
    public DistributedLockException(String s) {
        super(s);
    }
}
