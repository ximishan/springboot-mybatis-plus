package com.demo.mybatisplus.model.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author zhangfeng
 * @date 2018/12/4 下午1:47
 */
public class UpmsUserRolesDto {

    @NotEmpty(message = "用户不能为空")
    private String userId;

    private List<String> roleIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
