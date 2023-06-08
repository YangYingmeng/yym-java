package com.yym.springframework.stereotype;

import java.lang.annotation.*;


/**
 * @Description: 将类交给spring容器管理
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}
