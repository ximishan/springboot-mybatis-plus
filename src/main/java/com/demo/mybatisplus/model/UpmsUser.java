package com.demo.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author Zhangfeng
 * @since 2020-06-01
 */
public class UpmsUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId("user_id")
      private String userId;

    /**
     * 帐号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String realname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别(1男/2女)
     */
    private Integer sex;

    /**
     * 状态(0:正常,1:锁定)
     */
    private Boolean locked;

    /**
     * 创建时间
     */
    private Date createDts;

    /**
     * 更新时间
     */
    private Date updateDts;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getCreateDts() {
        return createDts;
    }

    public void setCreateDts(Date createDts) {
        this.createDts = createDts;
    }

    public Date getUpdateDts() {
        return updateDts;
    }

    public void setUpdateDts(Date updateDts) {
        this.updateDts = updateDts;
    }

    @Override
    public String toString() {
        return "UpmsUser{" +
        "userId=" + userId +
        ", username=" + username +
        ", password=" + password +
        ", realname=" + realname +
        ", avatar=" + avatar +
        ", phone=" + phone +
        ", email=" + email +
        ", sex=" + sex +
        ", locked=" + locked +
        ", createDts=" + createDts +
        ", updateDts=" + updateDts +
        "}";
    }
}
