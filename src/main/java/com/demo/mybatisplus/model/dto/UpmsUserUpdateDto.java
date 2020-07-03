package com.demo.mybatisplus.model.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author zhangfeng
 * @date 2018/12/3 下午5:37
 */
public class UpmsUserUpdateDto {

    /**
     * 编号
     *
     * @mbg.generated
     */
    @NotEmpty(message = "用户id不能为空")
    private String userId;

    /**
     * 姓名
     *
     * @mbg.generated
     */
    @NotEmpty(message = "用户姓名不能为空")
    private String realname;

    /**
     * 电话
     *
     * @mbg.generated
     */
    @NotEmpty(message = "用户手机号不能为空")
    private String phone;

    /**
     * 邮箱
     *
     * @mbg.generated
     */
    @Size(max = 50, message = "邮箱长度不能超过50")
    @Email(message = "邮箱格式不符合要求")
    private String email;

    /**
     * 性别
     *
     * @mbg.generated
     */
    @NotNull(message = "此处不能留空")
    private int sex;

    /**
     * 状态(0:正常,1:锁定)
     *
     * @mbg.generated
     */
    @NotNull(message = "锁标记不能为空")
    private boolean locked;

    private List<String> roleIdList;

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
