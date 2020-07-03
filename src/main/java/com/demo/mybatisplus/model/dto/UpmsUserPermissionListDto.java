package com.demo.mybatisplus.model.dto;


import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhangfeng
 * @date 2018/11/29 上午10:30
 */
public class UpmsUserPermissionListDto {

    @NotNull(message = "用户id不能为空")
    private String userId;

    @NotNull(message = "权限列表不能为空")
    private List<String> permissionIdList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getPermissionIdList() {
        return permissionIdList;
    }

    public void setPermissionIdList(List<String> permissionIdList) {
        this.permissionIdList = permissionIdList;
    }
}
