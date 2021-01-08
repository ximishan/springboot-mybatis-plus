package com.demo.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 全局基础字典
 * </p>
 *
 * @author Zhangfeng
 * @since 2021-01-03
 */
public class ConfigGlobal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "code_id", type = IdType.AUTO)
    private Integer codeId;

    /**
     * common编号
     */
    private String childCd;

    /**
     * common名称
     */
    private String childCodeNm;

    /**
     * 父code
     */
    private String parentCd;

    /**
     * 顺序
     */
    private Integer orders;


    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public String getChildCd() {
        return childCd;
    }

    public void setChildCd(String childCd) {
        this.childCd = childCd;
    }

    public String getChildCodeNm() {
        return childCodeNm;
    }

    public void setChildCodeNm(String childCodeNm) {
        this.childCodeNm = childCodeNm;
    }

    public String getParentCd() {
        return parentCd;
    }

    public void setParentCd(String parentCd) {
        this.parentCd = parentCd;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ConfigGlobal{" +
                "codeId=" + codeId +
                ", childCd=" + childCd +
                ", childCodeNm=" + childCodeNm +
                ", parentCd=" + parentCd +
                ", orders=" + orders +
                "}";
    }
}
