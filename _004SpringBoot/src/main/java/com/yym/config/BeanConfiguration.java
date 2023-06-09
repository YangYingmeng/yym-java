package com.yym.config;

import com.yym.aop.aspect.DynamicMethodParamAspect;
import com.yym.aop.aspect.LogAspect;
import com.yym.aop.handle.DynamicMethodParamHandler;
import com.yym.aop.handle.LogHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 注册相关的bean
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-17 0:04
 */
@Configuration
public class BeanConfiguration {
    @Bean
    public DynamicMethodParamAspect dynamicMethodParamAspect() {
        return new DynamicMethodParamAspect();
    }

    @Bean
    public DynamicMethodParamHandler dynamicMethodParamHandler() {
        return new DynamicMethodParamHandler();
    }

    @Bean
    public LogHandler logHandler () {
        return new LogHandler();
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
