package com.yym.middleware.whitelist;

import com.alibaba.fastjson.JSON;
import com.yym.middleware.whitelist.annotation.DoWhiteList;
import com.yym.middleware.whitelist.config.WhiteListProperties;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @Description: 切面逻辑 搭配自定义注解使用
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-06-11 19:11
 */
@Aspect
@Component
public class DoJoinPoint {

    private Logger logger = LoggerFactory.getLogger(DoJoinPoint.class);

    @Resource
    private String whiteListConfig;

    @Pointcut("@annotation(com.yym.middleware.whitelist.annotation.DoWhiteList)")
    public void addPoint() {

    }

    @Around("addPoint()")
    public Object doRouter(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取自定义注解, 自定义注解是在方法上使用
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DoWhiteList whiteList = method.getAnnotation(DoWhiteList.class);

        // 获取字段值
        String keyValue = getFiledValue(whiteList.key(), joinPoint.getArgs());
        logger.info("middleware whitelist handler method：{} value：{}", method.getName(), keyValue);
        if (null == keyValue || "".equals(keyValue)) return joinPoint.proceed();

        String[] split = whiteListConfig.split(",");

        // 白名单过滤
        for (String str : split) {
            if (keyValue.equals(str)) {
                return joinPoint.proceed();
            }
        }

        // 拦截
        return returnObject(whiteList, method);
    }

    /**
     * @Description: 获取属性值
     * @Param:  key
     * @Return:
     */
    private String getFiledValue(String filed, Object[] args) {
        String filedValue = null;
        for (Object arg : args) {
            try {
                if (null == filedValue || "".equals(filedValue)) {
                    filedValue = BeanUtils.getProperty(arg, filed);
                } else {
                    break;
                }
            } catch (Exception e) {
                if (args.length == 1) {
                    return args[0].toString();
                }
            }
        }
        return filedValue;
    }


    /**
     * @Description: 返回对象
     * @Param: DoWhiteList 配置类
     * @Param: Method 方法
     * @Return: Object
     */
    private Object returnObject(DoWhiteList whiteList, Method method) throws IllegalAccessException, InstantiationException {
        Class<?> returnType = method.getReturnType();
        String returnJson = whiteList.returnJson();
        if ("".equals(returnJson)) {
            return returnType.newInstance();
        }
        return JSON.parseObject(returnJson, returnType);
    }

}
