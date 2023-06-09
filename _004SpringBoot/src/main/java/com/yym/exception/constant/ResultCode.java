package com.yym.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 返回结果枚举类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-18 23:31
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * 测试异常结果
     */
    TEST_EXCEPTION_RESULT("1000", "测试异常结果", "管理员: yym"),
    /**
     * 登录请求过期(Security 异常)
     */
    ACCOUNT_LOGIN_REQUEST_EXPIRED("1001", "登录请求过期", "管理员: yym"),
    /**
     * 成功
     */
    SUCCESS("0000", "OK", "管理员: yym"),
    /**
     * 系统异常
     */
    FAIL("9999","系统异常！请联系管理员！", "管理员: yym");


    private final String code;
    private final String message;
    private final String admin;
}
