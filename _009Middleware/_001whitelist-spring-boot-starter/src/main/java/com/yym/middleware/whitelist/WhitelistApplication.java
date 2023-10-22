package com.yym.middleware.whitelist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 启动类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-22 20:51
 */
@SpringBootApplication
public class WhitelistApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhitelistApplication.class, args);
        System.out.println("+++ whitelistApplication start +++");
    }
}
