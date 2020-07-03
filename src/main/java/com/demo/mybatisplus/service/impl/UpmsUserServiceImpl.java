package com.demo.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mybatisplus.mapper.UpmsUserMapper;
import com.demo.mybatisplus.model.UpmsUser;
import com.demo.mybatisplus.service.IUpmsUserService;
import org.springframework.stereotype.Service;

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

}
