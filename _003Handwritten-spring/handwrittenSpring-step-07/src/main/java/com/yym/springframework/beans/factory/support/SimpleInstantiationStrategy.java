package com.yym.springframework.beans.factory.support;

import com.yym.springframework.beans.BeansException;
import com.yym.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description: 使用简单对象实例化策略(JDK 实例化)
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/4/25 14:01
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object... args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null != constructor) {
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
