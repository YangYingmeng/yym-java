package com.yym.springframework.context.annotation;


import java.lang.annotation.*;

/**
 * @Description: 配置作用域的注解 默认为 singleton, 可以获取到bean的作用域
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Scope {

    String value() default "singleton";
}
