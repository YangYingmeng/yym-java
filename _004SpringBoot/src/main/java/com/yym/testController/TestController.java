package com.yym.testController;

import com.yym.aop.handle.DynamicMethodParamHandler;
import com.yym.aop.handle.LogHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-16 22:37
 */
@RestController
public class TestController {

    final DynamicMethodParamHandler dynamicMethodParamHandler;
    final LogHandler logHandler;

    public TestController(DynamicMethodParamHandler dynamicMethodParamHandler, LogHandler logHandler) {
        this.dynamicMethodParamHandler = dynamicMethodParamHandler;
        this.logHandler = logHandler;
    }


    @GetMapping("/yym/1")
    public String test1() {
        dynamicMethodParamHandler.yymAnnotation("yym");
        return "yym";
    }

    @GetMapping("/yym/2")
    public String test2() {
        logHandler.testLogAnnotation("yym");
        return "yym";
    }

    @PostMapping("/yym/3")
    public String test3() {
        return "yym";
    }
}
