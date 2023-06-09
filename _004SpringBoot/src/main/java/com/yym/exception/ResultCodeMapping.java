package com.yym.exception;


import com.yym.exception.constant.ResultCode;

import java.lang.annotation.*;

/**
 * @Description: 自定义返回结果的mapping注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultCodeMapping {

    ResultCode code();

    Class<?>[] exception() default {};

}
