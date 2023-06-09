package com.yym.aop.annotation;


import java.lang.annotation.*;

/**
 * @Description: 自定义日志注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    // 设置所需参数和默认值
    String module() default "";

    String operate() default "";
}
