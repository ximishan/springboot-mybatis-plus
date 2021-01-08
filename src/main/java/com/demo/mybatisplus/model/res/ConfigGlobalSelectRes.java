package com.demo.mybatisplus.model.res;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ConfigGlobalSelectRes implements Serializable {

    @ApiModelProperty(notes = "key，用作传参")
    private String key;

    @ApiModelProperty(notes = "value, 用作展示")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}