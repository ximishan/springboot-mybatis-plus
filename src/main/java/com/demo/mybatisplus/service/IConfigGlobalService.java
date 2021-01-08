package com.demo.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.mybatisplus.model.ConfigGlobal;
import com.demo.mybatisplus.model.res.ConfigGlobalSelectRes;

import java.util.List;

/**
 * <p>
 * 全局基础字典 服务类
 * </p>
 *
 * @author Zhangfeng
 * @since 2021-01-03
 */
public interface IConfigGlobalService extends IService<ConfigGlobal> {

    List<ConfigGlobalSelectRes> queryCommonSelectList(String parentCd);

    List<ConfigGlobal> queryAll();

}
