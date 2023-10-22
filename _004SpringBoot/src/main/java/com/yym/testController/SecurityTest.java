package com.yym.testController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: security框架测试
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-09-26 16:27
 * security处理步骤
 * httpRequest -> filter -> AuthenticationManager 
 */
@RestController
@RequestMapping("/auth")
public class SecurityTest {

    @GetMapping("hello")
    public String test() {
        return "hello";
    }
}
