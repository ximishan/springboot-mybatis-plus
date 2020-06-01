package com.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mybatisplus.model.UpmsUser;
import com.demo.mybatisplus.model.res.UpmsUserRes;
import com.demo.mybatisplus.model.vo.ConditionVo;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author Zhangfeng
 * @since 2020-06-01
 */
public interface UpmsUserExtMapper{

    List<UpmsUser> selectAll();

    IPage<UpmsUserRes> selectPage(Page<UpmsUserRes> pages, ConditionVo conditionVo);
}
