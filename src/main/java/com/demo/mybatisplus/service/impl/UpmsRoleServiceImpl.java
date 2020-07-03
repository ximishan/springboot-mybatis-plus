package com.demo.mybatisplus.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mybatisplus.mapper.UpmsRoleMapper;
import com.demo.mybatisplus.model.UpmsRole;
import com.demo.mybatisplus.service.IUpmsRoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsRoleService实现
* @author: zhangfeng
* @Date: 2020/4/14
*/
@Service
@Transactional
public class UpmsRoleServiceImpl extends ServiceImpl<UpmsRoleMapper, UpmsRole> implements IUpmsRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsRoleServiceImpl.class);

    @Autowired
    UpmsRoleMapper upmsRoleMapper;

    @Override
    public long countRoleNameNotEqRoleId(String roleName, String roleId) {
        if (StringUtils.isBlank(roleName)) {
            throw new NullPointerException("roleName is empty");
        }
        QueryWrapper<UpmsRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", roleName);

        if (StringUtils.isNotBlank(roleId)) {
            queryWrapper.ne("role_id", roleId);
        }
        long count = upmsRoleMapper.selectCount(queryWrapper);
        return count;
    }

}