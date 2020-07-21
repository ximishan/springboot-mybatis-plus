package com.demo.mybatisplus.enums;

import com.demo.mybatisplus.constants.ApiConstant;

/**
 * @author RudeCrab
 * @description 响应码枚举
 */
public enum ResultCode {

    SUCCESS(200, "操作成功", ApiConstant.STATUS_SUCCESS),
    FAILED(1001, "响应失败", ApiConstant.STATUS_ERROR),
    VALIDATE_FAILED(1002, "参数校验失败", ApiConstant.STATUS_ERROR),
    TOKEN_ERROR(1003, "请求携带的token不正确", ApiConstant.STATUS_ERROR),
    REFRESH_TOKEN_EMPTY_ERROR(1004, "refreshToken 为空", ApiConstant.STATUS_ERROR),
    USERNAME_EMPTY_ERROR(1005, "username 为空", ApiConstant.STATUS_ERROR),
    REFRESH_TOKEN_NOT_EXIST_ERROR(1006, "refreshToken不存在", ApiConstant.STATUS_ERROR),
    CODE_EXPIRE_ERROR(1007, "验证码已经过期", ApiConstant.STATUS_ERROR),
    CODE_NOT_EXIST_ERROR(1008, "验证码已经过期", ApiConstant.STATUS_ERROR),


    ROLE_EXIST_FAILED(2000, "角色已经存在", ApiConstant.STATUS_ERROR),
    ROLE_NOT_EXIST_FAILED(2001, "角色不存在", ApiConstant.STATUS_ERROR),
    PERMISSION_NOT_EXIST_FAILED(2002, "权限ID不存在", ApiConstant.STATUS_ERROR),
    USERNAME_EXIST_FAILED(2003, "用户名已经存在", ApiConstant.STATUS_ERROR),
    PHONE_NUMBER_EXIST_FAILED(2004, "该手机号对应的用户不存在，请联系管理员申请", ApiConstant.STATUS_ERROR),


    BAD_REQUEST_ERROR(400, "Bad request", ApiConstant.STATUS_ERROR),
    UNAUTHORIZED_ERROR(401, "UNAUTHORIZED", ApiConstant.STATUS_ERROR),
    FORBIDDEN_ERROR(403, "Forbidden", ApiConstant.STATUS_ERROR),
    NOT_FOUND_ERROR(404, "not found", ApiConstant.STATUS_ERROR),


    ERROR(500, "未知错误", ApiConstant.STATUS_ERROR);

    private int code;
    private String msg;
    private boolean success;

    ResultCode(int code, String msg, boolean status) {
        this.code = code;
        this.msg = msg;
        this.success = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }
}