package com.demo.mybatisplus.exception;

/**
 * @author zhangfeng
 * @date 2020/3/20 4:07 下午
 */
public class LockedAccountException extends RuntimeException{

    public LockedAccountException(String message) {
        super(message);
    }

}
