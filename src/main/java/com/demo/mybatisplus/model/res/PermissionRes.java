package com.demo.mybatisplus.model.res;

import java.util.List;

/**
 * @author zhangfeng
 * @date 2020/4/26 5:42 下午
 */
public class PermissionRes {

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
    private String pid;

    /**
     * 名称
     *
     * @mbggenerated
     */
    private String name;

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

    private List<PermissionRes> children;

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

    public List<PermissionRes> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionRes> children) {
        this.children = children;
    }
}
