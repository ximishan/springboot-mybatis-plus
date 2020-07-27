package com.demo.mybatisplus.aop;

import com.demo.mybatisplus.annotation.MethodPerformanceLog;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @Before("pointcut()")
    public void exBefore(JoinPoint joinPoint){
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 打印请求相关参数
        LOGGER.info("========================================== Start ==========================================");
        // 打印请求 url
        LOGGER.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        LOGGER.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        LOGGER.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        LOGGER.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        // 打印请求入参
        try {
            String queryString = request.getQueryString();
            if (StringUtils.isNotBlank(queryString)) {
                LOGGER.info("Request Args   : {}", queryString);
            } else {
                LOGGER.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()[0]));
            }
        } catch (Exception e) {

        }
        LOGGER.info("========================================== END ==========================================");

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
