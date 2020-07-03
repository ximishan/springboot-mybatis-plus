package com.demo.mybatisplus.model.dto;

import javax.validation.constraints.NotNull;

/**
 * @author zhangfeng
 * @date 2020/4/14 11:00 上午
 */
public class PermissionDto {

    /**
     * 编号
     *
     * @mbggenerated
     */
    private String permissionId;

    /**
     * 所属上级
     *
     * @mbggenerated
     */
    @NotNull(message = "权限所属上级不能为空")
    private String pid;

    /**
     * 名称
     *
     * @mbggenerated
     */
    @NotNull(message = "权限名称不能为空")
    private String name;

    /**
     * 类型(1:目录,2:菜单,3:按钮)
     *
     * @mbggenerated
     */
    @NotNull(message = "权限类型不能为空")
    private Integer type;

    /**
     * 权限值
     *
     * @mbggenerated
     */
    private String permissionValue;

    /**
     * 路径
     *
     * @mbggenerated
     */
    private String uri;

    /**
     * 图标
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * 状态(0:禁止,1:正常)
     *
     * @mbggenerated
     */
    @NotNull(message = "权限状态不能为空")
    private Boolean status;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
