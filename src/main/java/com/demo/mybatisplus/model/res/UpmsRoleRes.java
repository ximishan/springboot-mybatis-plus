package com.demo.mybatisplus.model.res;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangfeng
 * @date 2020/6/2 11:08 上午
 */
public class UpmsRoleRes {

    /**
     * 编号
     */
    @TableId("role_id")
    @ApiModelProperty(notes = "角色id")
    private String roleId;

    /**
     * 角色名称
     */
    @ApiModelProperty(notes = "角色名称")
    private String name;

    /**
     * 角色标题
     */
    @ApiModelProperty(notes = "角色标题")
    private String title;

    /**
     * 角色描述
     */
    @ApiModelProperty(notes = "角色描述")
    private String description;

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
}
