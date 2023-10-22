package com.yym.middleware.whitelist.annotation;

import java.lang.annotation.*;

/**
 * @Description: 定义白名单注解
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-11 19:14
 */
@Retention(RetentionPolicy.RUNTIME)     // 元注解
@Target(ElementType.METHOD)             // 元注解 自定义注解用在什么地方
@Inherited
public @interface DoWhiteList {

    String key() default "";

    String returnJson() default "";
}
