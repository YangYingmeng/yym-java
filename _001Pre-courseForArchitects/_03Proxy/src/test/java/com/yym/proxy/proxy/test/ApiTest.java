package com.yym.proxy.proxy.test;

import com.yym.proxy.Person;
import com.yym.proxy.YymProxy;
import org.junit.Test;

/**
 * @Description: 测试类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-18 23:00
 */
public class ApiTest {
    @Test
    public void test_proxy() {
        YymProxy yymProxy = new YymProxy();
        Person yym = yymProxy.getProxy();
        yym.sing("海阔天空");
        yym.dance("华尔兹");
    }
}
