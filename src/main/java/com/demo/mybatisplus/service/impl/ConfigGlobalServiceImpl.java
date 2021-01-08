package com.demo.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mybatisplus.mapper.ConfigGlobalMapper;
import com.demo.mybatisplus.model.ConfigGlobal;
import com.demo.mybatisplus.model.res.ConfigGlobalSelectRes;
import com.demo.mybatisplus.service.IConfigGlobalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 全局基础字典 服务实现类
 * </p>
 *
 * @author Zhangfeng
 * @since 2021-01-03
 */
@Service
@Transactional
public class ConfigGlobalServiceImpl extends ServiceImpl<ConfigGlobalMapper, ConfigGlobal> implements IConfigGlobalService {

    @Override
    public List<ConfigGlobalSelectRes> queryCommonSelectList(String parentCd) {
        QueryWrapper<ConfigGlobal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_cd", parentCd);
        queryWrapper.orderByAsc("orders");
        List<ConfigGlobal> list = this.list(queryWrapper);
        List<ConfigGlobalSelectRes> selectResList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(item ->{
                ConfigGlobalSelectRes configGlobalSelectRes = new ConfigGlobalSelectRes();
                configGlobalSelectRes.setKey(item.getChildCd());
                configGlobalSelectRes.setValue(item.getChildCodeNm());
                selectResList.add(configGlobalSelectRes);
            });
        }
        return selectResList;
    }

    @Override
    public List<ConfigGlobal> queryAll() {
        QueryWrapper<ConfigGlobal> queryWrapper = new QueryWrapper<>();
        List<ConfigGlobal> list = list(queryWrapper);
        return list;
    }
}
