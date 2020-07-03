package com.demo.mybatisplus.model.res;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class UpmsUserRes {
    /**
     * 编号
     *
     * @mbggenerated
     */
    @ApiModelProperty(notes = "用户id")
    private String userId;

    /**
     * 帐号
     *
     * @mbggenerated
     */
    @ApiModelProperty(notes = "帐号")
    private String username;

    /**
     * 姓名
     *
     * @mbggenerated
     */
    @ApiModelProperty(notes = "姓名")
    private String realname;

    /**
     * 头像
     *
     * @mbggenerated
     */
    @ApiModelProperty(notes = "头像")
    private String avatar;

    /**
     * 电话
     *
     * @mbggenerated
     */
    @ApiModelProperty(notes = "电话")
    private String phone;

    /**
     * 邮箱
     *
     * @mbggenerated
     */
    @ApiModelProperty(notes = "邮箱")
    private String email;

    /**
     * 性别(1男/2女)
     *
     * @mbggenerated
     */
    @ApiModelProperty(notes = "性别(1男/2女)")
    private Integer sex;

    /**
     * 状态(0:正常,1:锁定)
     *
     * @mbggenerated
     */
    @ApiModelProperty(notes = "状态(0:正常,1:锁定)")
    private Boolean locked;

    @ApiModelProperty(notes = "角色id数组")
    private List<String> roleIdList;

    @ApiModelProperty(notes = "角色名称")
    private String roleNameStr;

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getRoleNameStr() {
        return roleNameStr;
    }

    public void setRoleNameStr(String roleNameStr) {
        this.roleNameStr = roleNameStr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}