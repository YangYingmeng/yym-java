package com.yym.aop.handle;

import com.yym.aop.annotation.DynamicMethodParam;

/**
 * @Description: 对外提供的处理方法
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-16 22:29
 */
public class DynamicMethodParamHandler {
    @DynamicMethodParam(param = "#event")
    public void yymAnnotation(String event) {
        System.out.println("调用 yymAnnotion 自定义注解 event:" + event);
    }
}
