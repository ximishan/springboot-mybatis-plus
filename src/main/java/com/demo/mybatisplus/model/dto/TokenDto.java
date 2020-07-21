package com.demo.mybatisplus.model.dto;

import javax.validation.constraints.NotNull;

/**
 * @author zhangfeng
 * @date 2020/4/14 3:53 下午
 */
public class TokenDto {

    @NotNull(message= "token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
