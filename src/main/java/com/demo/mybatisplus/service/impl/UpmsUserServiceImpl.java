package com.demo.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mybatisplus.mapper.UpmsUserExtMapper;
import com.demo.mybatisplus.model.UpmsUser;
import com.demo.mybatisplus.mapper.UpmsUserMapper;
import com.demo.mybatisplus.model.res.UpmsUserRes;
import com.demo.mybatisplus.model.vo.ConditionVo;
import com.demo.mybatisplus.service.IUpmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Zhangfeng
 * @since 2020-06-01
 */
@Service
public class UpmsUserServiceImpl extends ServiceImpl<UpmsUserMapper, UpmsUser> implements IUpmsUserService {

    @Autowired
    UpmsUserMapper upmsUserMapper;

    @Autowired
    UpmsUserExtMapper upmsUserExtMapper;

    @Override
    public IPage<UpmsUser> queryAllPage(Integer page, Integer size) {
        Page<UpmsUser> pages = new Page<>(page, size);
        // 查询条件
        QueryWrapper<UpmsUser> wrapper = new QueryWrapper();
        // 各种查询条件，根据前端返回进行动态拼接
//        wrapper.eq();
//        wrapper.orderByAsc();
//        wrapper.last();
        IPage<UpmsUser> upmsUserIPage = upmsUserMapper.selectPage(pages, wrapper);

        return upmsUserIPage;
    }

    @Override
    public IPage<UpmsUserRes> queryAllPageTwo(ConditionVo conditionVo) {
        Page<UpmsUserRes> pages = new Page<>(conditionVo.getCurrentPage(), conditionVo.getPageSize());
        IPage<UpmsUserRes> upmsUserIPage = upmsUserExtMapper.selectPage(pages, conditionVo);
        return upmsUserIPage;
    }
}
