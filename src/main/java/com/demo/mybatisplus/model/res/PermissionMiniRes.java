package com.demo.mybatisplus.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author zhangfeng
 * @date 2020/4/26 5:42 下午
 */
public class PermissionMiniRes {

    /**
     * 编号
     *
     * @mbggenerated
     */
    @JsonProperty("key")
    private String permissionId;

    /**
     * 所属上级
     *
     * @mbggenerated
     */
    private String pid;

    /**
     * 名称
     *
     * @mbggenerated
     */
    @JsonProperty("title")
    private String name;

    private List<PermissionMiniRes> children;

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

    public List<PermissionMiniRes> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionMiniRes> children) {
        this.children = children;
    }
}
