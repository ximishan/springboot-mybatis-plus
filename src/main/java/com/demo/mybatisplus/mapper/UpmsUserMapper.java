package com.demo.mybatisplus.mapper;

import com.demo.mybatisplus.model.UpmsUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author Zhangfeng
 * @since 2020-06-01
 */
public interface UpmsUserMapper extends BaseMapper<UpmsUser> {

    List<UpmsUser> selectAll();
}
