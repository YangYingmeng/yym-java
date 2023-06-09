package com.yym.aop.aspect;

import com.yym.aop.annotation.DynamicMethodParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @Description: 测试注解+aop实现功能
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-16 22:16
 */
@Aspect
public class DynamicMethodParamAspect {

    @Pointcut("@annotation(com.yym.aop.annotation.DynamicMethodParam)")
    public void methodAuth() {

    }

    @Around(value = "methodAuth()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("拦截的方法: " + method);
        DynamicMethodParam annotation = method.getAnnotation(DynamicMethodParam.class);
        System.out.println("入参: " + annotation.param());
        String value = generateKeyBySpEL(annotation.param(), joinPoint);
        System.out.println("解析入参: " + value);
        // 执行源方法
        return joinPoint.proceed();
    }

    // 解析SpEL表达式
    private SpelExpressionParser parserSpel = new SpelExpressionParser();
    // 获取给定方法的参数名数组
    private DefaultParameterNameDiscoverer parameterNameDiscoverer= new DefaultParameterNameDiscoverer();


    /**
     * @Description: 解析方法参数的SpEL表达式
     * @Param:
     * @Return:
     */
    public String generateKeyBySpEL(String key, ProceedingJoinPoint pjp) {
        Expression expression = parserSpel .parseExpression(key);
        EvaluationContext context = new StandardEvaluationContext();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Object[] args = pjp.getArgs();

        String[] paramNames = parameterNameDiscoverer.getParameterNames(methodSignature.getMethod());
        for(int i = 0 ; i < args.length ; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return expression.getValue(context).toString();
    }

}
