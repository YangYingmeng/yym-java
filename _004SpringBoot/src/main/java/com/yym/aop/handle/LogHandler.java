package com.yym.aop.handle;

import com.yym.aop.annotation.LogAnnotation;

/**
 * @Description: TODO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-17 19:15
 */
public class LogHandler {
    @LogAnnotation(module = "handler", operate = "测试日志注解")
    public void testLogAnnotation(String user) {
        System.out.println("调用 testLogAnnotation 方法");
    }
}
