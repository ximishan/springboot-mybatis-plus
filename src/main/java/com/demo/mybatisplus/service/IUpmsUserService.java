package com.demo.mybatisplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.mybatisplus.model.UpmsUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.mybatisplus.model.res.UpmsUserRes;
import com.demo.mybatisplus.model.vo.ConditionVo;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Zhangfeng
 * @since 2020-06-01
 */
public interface IUpmsUserService extends IService<UpmsUser> {

    IPage<UpmsUser> queryAllPage(Integer page, Integer size);

    IPage<UpmsUserRes> queryAllPageTwo(ConditionVo conditionVo);
}
