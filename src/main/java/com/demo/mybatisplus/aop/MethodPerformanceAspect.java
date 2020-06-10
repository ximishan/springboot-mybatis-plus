package com.demo.mybatisplus.aop;

import com.demo.mybatisplus.annotation.MethodPerformanceLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhangfeng
 * @date 2020/4/28 1:48 下午
 */
@Aspect
@Component
public class MethodPerformanceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodPerformanceAspect.class);

    @Pointcut("@annotation(com.demo.mybatisplus.annotation.MethodPerformanceLog)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String info = "统计接口调用总共时间";
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长
        long time = System.currentTimeMillis() - beginTime;

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();


        // 获取注解上的操作描述
        MethodPerformanceLog methodPerformanceLog = method.getAnnotation(MethodPerformanceLog.class);
        String methodDesc = null;
        if (methodPerformanceLog != null) {
            methodDesc = methodPerformanceLog.desc();
        }
        // 打印时间
        LOGGER.info("{}, methodName={}, 描述={}，消耗时间={}ms", info, method.getName(), methodDesc, time);
        return result;
    }
}
