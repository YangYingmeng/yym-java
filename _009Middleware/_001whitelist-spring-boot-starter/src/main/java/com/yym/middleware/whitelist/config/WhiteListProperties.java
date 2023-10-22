package com.yym.middleware.whitelist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 白名单配置类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-11 19:24
 */
@ConfigurationProperties(prefix = "yym.whitelist")
public class WhiteListProperties {

    private String users;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
