package com.demo.mybatisplus.model.res;

import io.swagger.annotations.ApiModelProperty;


public class UpmsUserMiniRes {
    /**
     * 编号
     *
     * @mbggenerated
     */
    @ApiModelProperty(notes = "用户id")
    private String userId;

    /**
     * 姓名
     *
     * @mbggenerated
     */
    @ApiModelProperty(notes = "姓名")
    private String realname;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}