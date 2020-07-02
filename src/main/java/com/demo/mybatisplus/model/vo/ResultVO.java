package com.demo.mybatisplus.model.vo;

import com.demo.mybatisplus.constants.ApiConstant;
import com.demo.mybatisplus.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiModel
public class ResultVO<T> {

    @ApiModelProperty(value = "状态", notes = "默认是true")
    private boolean success = true;

    @ApiModelProperty(value = "状态码", notes = "默认1000是成功")
    private int code = 1000;
    @ApiModelProperty(value = "响应信息", notes = "来说明响应情况")
    private String msg;
    @ApiModelProperty(value = "响应的具体数据")
    private T data;

    public ResultVO() {
    }

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }


    public ResultVO(int code, String msg, boolean status, T data) {
        this.code = code;
        this.msg = msg;
        this.success = status;
        this.data = data;
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public ResultVO(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.success = resultCode.isSuccess();
    }

    public static ResultVO success() {
        return success("操作成功");
    }

    public static ResultVO success(String msg) {
        return success(msg, null);
    }

    public static ResultVO successData(Object data) {
        return success("操作成功", data);
    }

    public static ResultVO successList(List list) {
        Map result = new HashMap();
        result.put("rows", list);
        return successData(result);
    }


    public static ResultVO success(String msg, Object data) {
        return new ResultVO(ApiConstant.CODE_SUCCESS, msg,ApiConstant.STATUS_SUCCESS, data);
    }

    public static ResultVO error(String msg) {
        ResultVO resultBean = new ResultVO();
        resultBean.setMsg(msg);
        resultBean.setSuccess(ApiConstant.STATUS_ERROR);
        resultBean.setCode(ApiConstant.CODE_ERR);
        return resultBean;
    }

    public static ResultVO error(ResultCode resultCode) {
        return new ResultVO(resultCode);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}