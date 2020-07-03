package com.demo.mybatisplus.model.dto;


import com.demo.mybatisplus.model.vo.ConditionVo;

/**
 * @author zhangfeng
 * @date 2018/11/29 上午10:45
 */
public class UpmsRoleListDto extends ConditionVo {

    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}