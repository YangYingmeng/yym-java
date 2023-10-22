package com.yym.middleware.whitelist.controller;


import com.yym.middleware.whitelist.annotation.DoWhiteList;
import com.yym.middleware.whitelist.entity.UserInfo;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @Description: 测试类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-22 20:52
 */
@RestController
public class TestController {

    /**
     * 通过：<a href="http://localhost:8081/api/queryUserInfo?userId=aaa">...</a>
     * 拦截：<a href="http://localhost:8081/api/queryUserInfo?userId=123">...</a>
     */
    @DoWhiteList(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"}")
    @RequestMapping(path = "/api/queryUserInfo", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId) {
        System.out.println("用户信息: " + userId);
        return new UserInfo("yym:" + userId, 19, "....");

    }
}
