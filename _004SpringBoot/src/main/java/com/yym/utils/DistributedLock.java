package com.yym.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 分布式锁注解
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-12 11:10
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    String value(); // 锁的名称
    long timeout() default 5000; // 锁的超时时间，默认5秒
}
