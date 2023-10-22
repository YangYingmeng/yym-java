package com.yym.middleware.whitelist.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 配置类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-11 19:26
 */
@Configuration      // 标识组件
// 只有当WhiteListProperties在当前类路径上, 才会实例化一个类
// ConditionalOnClass/Bean/Expression/MissingBean/MissingClass
@ConditionalOnClass(WhiteListProperties.class)
@EnableConfigurationProperties(WhiteListProperties.class)   // 使配置类生效, 将配置类交给spring管理
public class WhiteListAutoConfigure {

    // 将配置类注入
    @Bean("whiteListConfig")
    @ConditionalOnMissingBean   // 只会实例化一个该Bean对象
    public String whiteListConfig(WhiteListProperties whiteListProperties) {
        return whiteListProperties.getUsers();
    }
}
