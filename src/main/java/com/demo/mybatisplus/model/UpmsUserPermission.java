package com.demo.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户权限关联表
 * </p>
 *
 * @author Zhangfeng
 * @since 2020-06-01
 */
public class UpmsUserPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId("user_permission_id")
    private String userPermissionId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 权限编号
     */
    private String permissionId;

    private Date createDts;


    public String getUserPermissionId() {
        return userPermissionId;
    }

    public void setUserPermissionId(String userPermissionId) {
        this.userPermissionId = userPermissionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public Date getCreateDts() {
        return createDts;
    }

    public void setCreateDts(Date createDts) {
        this.createDts = createDts;
    }

    @Override
    public String toString() {
        return "UpmsUserPermission{" +
                "userPermissionId=" + userPermissionId +
                ", userId=" + userId +
                ", permissionId=" + permissionId +
                ", createDts=" + createDts +
                "}";
    }
}
