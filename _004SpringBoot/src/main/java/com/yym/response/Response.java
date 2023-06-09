package com.yym.response;

import com.yym.exception.constant.ResultCode;
import lombok.Getter;

/**
 * @Description: 基础返回对象
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-19 0:07
 */
@Getter
public class Response<T> {

    // 响应状态码
    private final String code;

    // 响应消息
    private final String msg;

    // 业务数据
    private final T data;

    public Response(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    public static ResponseBuilder data(Object data) {
        return builder().data(data);
    }

    public static Response<Void> ok() {
        return builder().ok();
    }

    public static Response<Void> fail(ResultCode resultCode) {
        return builder().fail(resultCode);
    }

    public static class ResponseBuilder {

        private Object data;

        public ResponseBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public <T> Response<T> ok() {
            return ok(ResultCode.SUCCESS.getMessage());
        }

        public <T> Response<T> ok(String msg) {
            return build(ResultCode.SUCCESS.getCode(), msg);
        }

        public <T> Response<T> fail(ResultCode resultCode) {
            return build(resultCode.getCode(), resultCode.getMessage());
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        private <T> Response<T> build(String resultCode, String msg) {
            return new Response(resultCode, msg, data);
        }

    }

}
