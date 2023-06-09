package com.yym.aop.aspect;

import com.yym.aop.annotation.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Description: 日志切面类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-17 18:50
 */
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.yym.aop.annotation.LogAnnotation)")
    public void pointcut() {

    }


    /**
     * @Description: 通知方法
     */
    @Around("pointcut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时长
        long startTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 执行的时长
        long time = System.currentTimeMillis() - startTime;
        // 记录日志
        recordLog(joinPoint, time);
        return result;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        log.info("======================== 日志记录开始 ========================");
        log.info("日志模块: {}", annotation.module());
        log.info("日志操作: {}",  annotation.operate());
        log.info("请求方法: {}", joinPoint.getTarget().getClass().getName() + "." + methodSignature.getName() + "()");
        log.info("请求参数: {}",  joinPoint.getArgs()[0]);
        log.info("请求时长: {}",  time);
        log.info("请求IP: {}", getRequestIP());
        log.info("======================== 日志记录结束 ========================");

    }


    /**
     * @Description: 获取请求IP地址
     */
    private String getRequestIP() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        assert request != null;
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
