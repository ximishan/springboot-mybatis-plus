package com.demo.mybatisplus.annotation;

import java.lang.annotation.*;

/**
 * 该注解统计接口的性能
 * @author zhangfeng
 * @discription
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface MethodPerformanceLog {

    String desc() default "";

}