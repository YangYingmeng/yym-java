package com.yym.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: springBoot启动类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-10-09 23:40
 */
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
        System.out.println("+++ Security start +++");
    }
}
