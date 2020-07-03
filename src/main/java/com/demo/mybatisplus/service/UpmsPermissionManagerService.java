package com.demo.mybatisplus.service;

import com.demo.mybatisplus.model.UpmsRolePermission;
import com.demo.mybatisplus.model.UpmsUserPermission;
import com.demo.mybatisplus.model.dto.*;
import com.demo.mybatisplus.model.vo.ResultVO;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfeng
 * @date 2020/4/14 11:21 上午
 */
public interface UpmsPermissionManagerService {

    ResultVO getUserRoleList(UpmsRoleListDto upmsRoleListDto);

    ResultVO insertRole(UpmsRoleAddDto dto);

    ResultVO removeRole(String roleId);

    ResultVO getAllPermissionList(UpmsRoleListDto dto);

    ResultVO<Map<String, Object>> removePermissionById(String permissionId);

    ResultVO addPermission(PermissionDto dto);

    ResultVO updatePermission(PermissionDto dto);

    ResultVO<Map<String, Object>> getUserPermissionListByRoleId(String roleId);

    List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(String roleId);

    List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(String userId);

    ResultVO updatePermissionListByRoleId(UpmsUserPermissionListDto dto);

    long countUpmsUserByUsername(String username);

    ResultVO<Map<String, Object>> addUpmsUser(UserRegisterDto dto);

    ResultVO getUserPermissionListByUserId(String userId);

    ResultVO<Map<String, Object>> removeUpmsUser(String userId);

    ResultVO updateUpmsUser(UpmsUserUpdateDto dto);

    ResultVO<Map<String, Object>> updatePermissionListByUserId(UpmsUserPermissionListDto dto);

    ResultVO getUpmsUserList(UpmsRoleListDto upmsRoleListDto);

    ResultVO findUpmsUserRole(String userId);

    ResultVO updateUpmsUserRoles(UpmsUserRolesDto upmsUserRolesDto);

    ResultVO resetPassword(String userId);

    ResultVO updateUserLock(String userId);

    ResultVO getUpmsUserSelectList();

}
