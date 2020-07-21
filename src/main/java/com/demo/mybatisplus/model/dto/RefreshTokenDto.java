package com.demo.mybatisplus.model.dto;

import javax.validation.constraints.NotNull;

/**
 * @author zhangfeng
 * @date 2020/4/14 3:53 下午
 */
public class RefreshTokenDto {

    @NotNull(message= "用户名不能为空")
    private String username;

    @NotNull(message= "refreshToken不能为空")
    private String refreshToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
