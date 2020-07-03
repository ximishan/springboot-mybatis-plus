package com.demo.mybatisplus.model.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.List;

public class UserRegisterDto {

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能留空")
    //@Size(min = 5, max = 15, message = "用户名长度应为5-15个字符，请勿包含姓名/身份证/银行卡等隐私信息", groups = Register.class)
    //@Pattern(regexp = "[0-9a-zA-Z\u4e00-\u9fa5_]+", message = "用户名仅支持中英文、数字和下划线", groups = Register.class)
    private String username;

    /**
     * 姓名
     *
     * @mbg.generated
     */
    @NotEmpty(message = "用户姓名不能为空")
    private String realname;

    /**
     * 邮箱
     */
    /*@NotEmpty(message = "邮箱不能留空", groups = {Register.class})
    @Size(max = 50, message = "邮箱长度不能超过50", groups = {Register.class})
    @Email(message = "邮箱格式不符合要求", groups = {Register.class})*/
    private String email;

    /**
     * 电话
     *
     * @mbg.generated
     */
    @NotEmpty(message = "用户手机号不能为空")
    @Size(max = 11,min = 11,message = "手机号位数错误")
    private String phone;

    /**
     * 性别(0:女，1:男，2:不愿透露)
     */
    @NotNull(message = "此处不能留空")
    @Range(min = 0, max = 2, message = "请选择给定的性别")
    private int sex;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能留空")
    @Size(min = 6, max = 16, message = "密码长度应为6-16个字符")
    @Pattern(regexp = "[0-9a-zA-Z\\p{Punct} ”]+", message = "密码仅支持字母、数字及标点符号")
    private String password;

    //(byte) 0 未锁定   1 锁定
    private boolean locked;

    private List<String> roleIdList;

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
