package com.yym.springframework.beans;

import com.yym.springframework.beans.bean.UserService;
import org.junit.Test;

/**
 * @Description: 测试案例
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-04-22 18:21
 */
public class ApiTest {

    @Test
    public void test_beanFactory() {

        // 1. 初始化beanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2. 注入bean, 此处bean的实例化是通过构造函数的参数形式传递, 把bean实例化的信息也传入beanFactory
        //    我们需要的是只将bean的类信息注册到beanFactory中
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
