package com.yym.springframework.beans;

import com.yym.springframework.beans.bean.UserService;
import com.yym.springframework.beans.factory.config.BeanDefinition;
import com.yym.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @Description: 测试
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 21:28
 */
public class ApiTest {

    @Test
    public void test_beanFactory() {
        // 1. 初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3. 第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        // 4. 第二次获取bean
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService1.queryUserInfo();
    }
}
