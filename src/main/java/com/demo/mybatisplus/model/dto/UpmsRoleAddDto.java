package com.demo.mybatisplus.model.dto;


import javax.validation.constraints.NotEmpty;

/**
 * @author zhangfeng
 * @date 2018/11/29 下午2:32
 */
public class UpmsRoleAddDto {

    private String roleId;

    @NotEmpty(message = "角色名称不能为空")
    private String name;

    private String description;

    @NotEmpty(message = "角色标题不能为空")
    private String title;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
