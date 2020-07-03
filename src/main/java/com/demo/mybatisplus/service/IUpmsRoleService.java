package com.demo.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.mybatisplus.model.UpmsRole;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author Zhangfeng
 * @since 2020-06-01
 */
public interface IUpmsRoleService extends IService<UpmsRole> {

    long countRoleNameNotEqRoleId(String roleName, String roleId);

}
