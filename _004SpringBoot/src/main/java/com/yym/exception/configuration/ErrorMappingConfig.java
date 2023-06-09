package com.yym.exception.configuration;

import com.yym.exception.ResultCodeMapping;
import com.yym.exception.ResultException;
import com.yym.exception.ServiceException;
import com.yym.exception.constant.ResultCode;
import com.yym.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 全局错误异常捕获
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-19 0:02
 */
@Slf4j
@RestControllerAdvice // 开启全局异常捕获
public class ErrorMappingConfig {


    /**
     * @Description: 当响应状态码为 400 并且 参数异常时 只获取异常的msg
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String convertIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }

    /**
     * @Description: 当异常为自定义结果异常时, 打印响应状态码和日志 返回失败结果以及状态码
     */
    @ExceptionHandler(ResultException.class)
    public Response<Void> convertResultException(ResultException e) {
        ResultCode resultCode = e.getResultCode();
        log.error("Result code: {} msg: {}", resultCode.getCode(), resultCode.getMessage());
        return Response.fail(resultCode);
    }

    /**
     * @Description: 当异常为Security异常时, 打印日志 返回失败结果以及状态码
     */
//    @ExceptionHandler(AuthenticationException.class)
//    public Response<Void> convertResultException(AuthenticationException e) {
//        ResultCode resultCode = ResultCode.ACCOUNT_LOGIN_REQUEST_EXPIRED;
//        log.error("Result AuthenticationException:{} code: {} msg: {}",e.getMessage(), resultCode.getCode(), resultCode.getMessage());
//        return Response.fail(resultCode);
//    }

    /**
     * @Description: 当异常为Service异常时, 打印日志 转换异常状态码 返回失败结果以及状态码
     */
    @ExceptionHandler(ServiceException.class)
    public Response<Void> convertServiceException(ServiceException e) {
        log.error("Service exception msg:{}", e.getMessage());
        ResultCode resultCode = mappingServiceExceptionToResultCode(e);
        log.error("Result code:{} msg:{}", resultCode.getCode(), resultCode.getMessage());
        return Response.fail(resultCode);
    }

    /**
     * 处理其它 Exception 异常
     */
    @ExceptionHandler(value = Exception.class)
    public Response<Void> convertException(Exception e) {
        e.printStackTrace();
        if(e instanceof ResultException){
            return convertResultException((ResultException) e);
        }
//        if(e instanceof AuthenticationException){
//            return convertResultException((AuthenticationException) e);
//        }
        if(e instanceof ServiceException){
            return convertServiceException((ServiceException) e);
        }
        // 记录异常日志
        log.error("exception msg:{}", e.getMessage());
        // 返回 ERROR
        return Response.fail(ResultCode.FAIL);
    }

    /**
     * @Description: 转换异常状态码
     */
    private ResultCode mappingServiceExceptionToResultCode(ServiceException e) {
        // 捕获 @ResultCodeMapping 标识的异常 获取状态码
        ResultCodeMapping resultCodeMapping = e.getClass().getDeclaredAnnotation(ResultCodeMapping.class);
        if (resultCodeMapping != null) {
            return resultCodeMapping.code();
        }
        // 如果异常未定义 则抛出非法异常
        throw new IllegalStateException("Unmapping exception:" + e.getClass().getName());
    }

}
