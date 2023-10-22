package com.yym.middleware.whitelistTest;

import com.yym.middleware.whitelist.annotation.DoWhiteList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 白名单测试
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-11 20:23
 */
@RestController
@Slf4j
public class UserController {

    /**
     * 通过：http://localhost:8081/api/queryUserInfo?userId=aaa
     * 拦截：http://localhost:8081/api/queryUserInfo?userId=123
     */
    @DoWhiteList(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"}")
    @RequestMapping(path = "/api/queryUserInfo", method = RequestMethod.GET)
    public String queryUserInfo(@RequestParam String userId) {
        log.info("查询用户信息，userId：{}", userId);
        return new UserInfo("虫虫:" + userId, 19, "上海市浦东新区").toString();
    }

}
