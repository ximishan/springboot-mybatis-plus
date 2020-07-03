package com.demo.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author Zhangfeng
 * @since 2020-06-01
 */
public class UpmsRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId("role_id")
    private String roleId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标题
     */
    private String title;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Long ctime;

    /**
     * 排序
     */
    private Long orders;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "UpmsRole{" +
                "roleId=" + roleId +
                ", name=" + name +
                ", title=" + title +
                ", description=" + description +
                ", ctime=" + ctime +
                ", orders=" + orders +
                "}";
    }
}
