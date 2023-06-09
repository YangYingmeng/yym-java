package com.yym.exception;

import com.yym.exception.constant.ResultCode;

/**
 * @Description: 响应异常
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-18 23:39
 */
public class ResultException extends RuntimeException {

    /**
     *异常代码
     */
    private final ResultCode resultCode;

    public ResultException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
