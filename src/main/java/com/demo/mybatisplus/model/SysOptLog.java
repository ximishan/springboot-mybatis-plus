package com.demo.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author Zhangfeng
 * @since 2020-09-22
 */
public class SysOptLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * userId
     */
    private String userId;

    /**
     * username
     */
    private String username;

    /**
     * ip
     */
    private String ip;

    /**
     * 接口花费时间
     */
    private Long totalTime;

    /**
     * 参数
     */
    private String params;

    /**
     * 方法名
     */
    private String methodDesc;

    /**
     * 返回值json
     */
    private String result;

    /**
     * 请求的url
     */
    private String url;

    private Date createDts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDts() {
        return createDts;
    }

    public void setCreateDts(Date createDts) {
        this.createDts = createDts;
    }

    @Override
    public String toString() {
        return "SysOptLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", username=" + username +
                ", ip=" + ip +
                ", totalTime=" + totalTime +
                ", params=" + params +
                ", methodDesc=" + methodDesc +
                ", result=" + result +
                ", url=" + url +
                ", createDts=" + createDts +
                "}";
    }
}
