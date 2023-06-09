package com.yym.exception.configuration;

import com.yym.utils.json.jackson.JackSon;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * @Description: 日志自动打印
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-19 12:31
 */
@Slf4j
@Aspect
@Component
public class AutoLoggingAspect {

    @Value("${autoLogging.enableGet:false}")
    private boolean enableGet;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && !target(com.yym.exception.configuration.ErrorMappingConfig)")
    private void anyRestController() {

    }

    @Around("anyRestController()")
    private Object logAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed(joinPoint.getArgs());
            long endTime = System.currentTimeMillis();
            String requestLog = buildRequestLog(joinPoint, result, endTime - startTime);
            if (StringUtils.hasText(requestLog)) {
                log.info(requestLog);
            }
            return result;
        } catch (Throwable e) {
            long endTime = System.currentTimeMillis();
            logger.info(buildRequestLog(joinPoint, "exception: " + e.getMessage(), endTime - startTime));
            throw e;
        }
    }

    /**
     * @Description: 构建需要打印的日志
     */
    private String buildRequestLog(ProceedingJoinPoint joinPoint, Object response, long duration) {
        try {
            Method method = getJoinPointMethod(joinPoint);
            if (null == method || !shouldAutoLogging(method)) {
                return null;
            }
            String path = extractEndPoint(method);
            String userId = extractUserId(joinPoint, method);
            String responseStr;
            if (response instanceof ResponseEntity) {
                Object body = ((ResponseEntity<?>) response).getBody();
                responseStr = JackSon.toJSONString(body);
            } else {
                responseStr = JackSon.toJSONString(response);
            }
            return "===> Path: " + path + " | " +
                    "User: " + userId + " | " +
                    "Duration: " + duration + "ms | " +
                    "Params: " + extractRequestParams(joinPoint, method) + " | " +
                    "Request: " + extractRequestBody(joinPoint, method) + " | " +
                    "Response: " + responseStr;
        } catch (Exception e) {
            log.error("[buildRequestLog] exception: ", e);
            return null;
        }
    }

    /**
     * @Description: 获取请求体
     */
    private String extractRequestBody(ProceedingJoinPoint joinPoint, Method method) {
        Object[] args = joinPoint.getArgs();
        Annotation[][] annotations = method.getParameterAnnotations();
        int paramIndex = 0;
        for (Annotation[] annotation : annotations) {
            for (Annotation item : annotation) {
                if (RequestBody.class == item.annotationType()) {
                    return JackSon.toJSONString(args[paramIndex]);
                }
            }
            paramIndex++;
        }
        return null;
    }

    /**
     * @Description: 获取请求参数
     */
    private String extractRequestParams(ProceedingJoinPoint joinPoint, Method method) {
        // 获取切点标识方法的 参数列表
        Object[] args = joinPoint.getArgs();
        // 每个方法有多个参数可以有多个注解标识 [参数位置][annotation1, annotation2]
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        StringBuilder builder = new StringBuilder();
        int paramIndex = 0;
        for (Annotation[] annotation : parameterAnnotations) {
            for (Annotation item : annotation) {
                if (RequestParam.class == item.annotationType()) {
                    RequestParam requestParamAnnotation = (RequestParam) item;
                    String paramName = requestParamAnnotation.value();
                    String paramValue = String.valueOf(args[paramIndex]);
                    builder.append(String.format("%s=%s", paramName, paramValue));
                    builder.append('&');
                }
            }
            paramIndex ++;
        }
        return builder.toString();
    }

    /**
     * @Description: 获取用户ID, 接口被 AuthenticationPrincipal 标记的 获取用户信息
     */
    private String extractUserId(ProceedingJoinPoint joinPoint, Method method) {
//        Object[] args = joinPoint.getArgs();
//        Annotation[][] annotations = method.getParameterAnnotations();
//        int paramIndex = 0;
//        for (Annotation[] annotation : annotations) {
//            for (Annotation item : annotation) {
//                if (AuthenticationPrincipal.class == item.annotationType()) {
//                    if (args[paramIndex] instanceof CharSequence) {
//                        return args[paramIndex].toString();
//                    } else {
//                        return JackSon.toJSONString(args[paramIndex]);
//                    }
//                }
//            }
//            paramIndex++;
//        }
        return "yym";
    }


    /**
     * @Description: 获取对应类型的端点路径 @PostMapping(value = {"/path1"})
     */
    private String extractEndPoint(Method method) {
        Annotation[] annotations = method.getAnnotations();
        String result = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof PostMapping) {
                result = ((PostMapping) annotation).value()[0];
            } else if (annotation instanceof DeleteMapping) {
                result = ((DeleteMapping) annotation).value()[0];
            } else if (annotation instanceof PutMapping) {
                result = ((PutMapping) annotation).value()[0];
            } else if (annotation instanceof RequestMapping) {
                RequestMapping requestMapping = (RequestMapping) annotation;
                if (requestMapping.method().length > 0) {
                    RequestMethod requestMethod = requestMapping.method()[0];
                    switch (requestMethod) {
                        case POST:
                        case DELETE:
                        case PUT:
                            result = requestMapping.value()[0];
                    }
                }
            }
        }
        return result;
    }


    /**
     * @Description: GET 请求不进行日志记录
     */
    private boolean shouldAutoLogging(Method method) {
        Annotation[] annotations = method.getAnnotations();
        boolean result = true;
        for (Annotation annotation : annotations) {
            if (annotation instanceof GetMapping) {
                result = enableGet;
                break;
            } else if (annotation instanceof RequestMapping) {
                RequestMapping mapping = (RequestMapping) annotation;
                if (mapping.method().length > 0) {
                    RequestMethod requestMethod = mapping.method()[0];
                    if (requestMethod == RequestMethod.GET) {
                        result = enableGet;
                    }
                    break;
                }
            }
        }
        return result;
    }

    /**
     * @Description: 获取切点标识的方法
     */
    private Method getJoinPointMethod(ProceedingJoinPoint joinPoint) {
        // 获取切点标识的方法名
        String methodName = joinPoint.getSignature().getName();
        // 找到方法对应的类
        Class<?> clazz = joinPoint.getSignature().getDeclaringType();
        // 获取类中所有的方法
        Method[] methods = clazz.getDeclaredMethods();
        // 通过方法名判断 找到和方法名一致的方法返回
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return method;
            }
        }
        log.warn("Can not found join point method");
        return null;
    }
}
