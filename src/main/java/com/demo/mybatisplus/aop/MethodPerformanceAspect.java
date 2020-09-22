package com.demo.mybatisplus.aop;

import com.demo.mybatisplus.annotation.MethodPerformanceLog;
import com.demo.mybatisplus.mapper.SysOptLogMapper;
import com.demo.mybatisplus.model.SysOptLog;
import com.demo.mybatisplus.utils.JsonUtil;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangfeng
 * @date 2020/4/28 1:48 下午
 */
@Aspect
@Component
public class MethodPerformanceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodPerformanceAspect.class);

    @Autowired
    private SysOptLogMapper sysOptLogMapper;


    @Pointcut("@annotation(com.demo.mybatisplus.annotation.MethodPerformanceLog)")
    public void pointcut() {
    }

    /**
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Before("pointcut()")
    public void exBefore(JoinPoint joinPoint){
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 打印请求相关参数
        LOGGER.info("========================================== Start ==========================================");
        // 打印请求 url
        LOGGER.info("URL            : {}", request.getRequestURL().toString());

        // 打印 Http method
        LOGGER.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        LOGGER.info("Class Method   : {}.{}", signature.getDeclaringTypeName(), signature.getName());
        // 打印请求的 IP
        LOGGER.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        try {
            Object[] args = joinPoint.getArgs();
            if (notInMethod().contains(signature.getDeclaringTypeName()+"."+signature.getName())) {
                LOGGER.info("Request Args   : {}");
            } else {
                if (args.length>0) {
                    LOGGER.info("Request Args   : {}", new Gson().toJson(args[0]));
                } else {
                    LOGGER.info("Request Args   : {}");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        //保存log
        try {
            saveLog(point, time, result, methodDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 打印时间
        LOGGER.info("[{}], methodName=[{}], 描述=[{}]，消耗时间=[{}]ms", info, method.getName(), methodDesc, time);
        return result;
    }

    public List notInMethod() {
        List<String> notInMethod = new ArrayList<>();
//        notInMethod.add("com.jessieray.energy.controller.EquipOilRecordController.download");
        return notInMethod;
    }

    void saveLog(ProceedingJoinPoint joinPoint, long time, Object result, String methodDesc){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SysOptLog sysOptLog = new SysOptLog();
        String userId = null;
        String username = null;
        try {
            userId = request.getAttribute("userId").toString();
            username = request.getAttribute("username").toString();
        } catch (Exception e) {

        }

        sysOptLog.setUserId(userId);
        sysOptLog.setUsername(username);
        String ip = request.getRemoteAddr();
        sysOptLog.setIp(ip);
        sysOptLog.setTotalTime(time);

        Object[] args = joinPoint.getArgs();
        if (notInMethod().contains(signature.getDeclaringTypeName()+"."+signature.getName())) {

        } else {
            if (args.length>0) {
                sysOptLog.setParams(JsonUtil.beanToJson(args[0]));
            }
        }
        sysOptLog.setUrl(request.getRequestURL().toString());
        sysOptLog.setMethodDesc(methodDesc);
        sysOptLog.setResult(JsonUtil.beanToJson(result));
        sysOptLog.setCreateDts(new Date());
        sysOptLogMapper.insert(sysOptLog);

    }
}
