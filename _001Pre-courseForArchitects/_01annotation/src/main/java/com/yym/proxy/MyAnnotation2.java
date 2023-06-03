package com.yym.proxy;

/*
*   自定义注解
* */
public @interface MyAnnotation2 {
    /*
    *   1. 无参方法作为成员变量, 类型是String[] 名字为value, 又叫做配置参数
    *   2. 如果只有一个配置参数, 名称为value, 赋值时value可以省略
    *   3. 如果配置了配置参数, 在使用注解的时候必须赋值
    * */
    String[] value() default {"123"}; // 可以配置默认值, 则不需要赋值
}
