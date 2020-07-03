package com.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.demo.mybatisplus.model.UpmsPermission;
import com.demo.mybatisplus.model.UpmsRole;
import com.demo.mybatisplus.model.UpmsUser;
import com.demo.mybatisplus.model.res.PermissionMiniRes;
import com.demo.mybatisplus.model.res.PermissionRes;
import com.demo.mybatisplus.model.res.UpmsRoleRes;
import com.demo.mybatisplus.model.res.UpmsUserRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UpmsPermissionExtMapper {

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(String upmsUserId);

    /**
     *
     */
    List<PermissionRes> selectPermissionResListByUpmsUserId(String upmsUserId);

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserId(String upmsUserId);


    /**
     * 根据用户id获取用户的权限，不包含角色权限
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> getUpmsPermissionByUpmsUserId(String upmsUserId);

    /**
     * 查询所有的权限数量
     * @param map
     * @return
     */
    int countPermissionList(Map map);

    /**
     * 查询所有的权限列表
     * @param map
     * @return
     */
    List<UpmsPermission> getPermissionList(Map map);

    /**
     * 查询角色下所有用户
     * @param map
     * @return
     */
    List<UpmsRole> getUpmsRoleUserList(Map map);

    int deleteUserPermissionByUserId(String userId);

    int deleteRolePermissionByRoleId(String roleId);

    int deleteUserPermissionByPermissionId(String permissionId);

    int deleteRolePermissionByPermissionId(String permissionId);

    int deleteUserRoleByUserId(String userId);

    int deleteUserRoleByRoleId(String roleId);

    /**
     * 查询最大的 orders
     * @return
     */
    int countMaxOrders();

    /**
     * 所有的用户列表分页
     * @param pages
     * @param query
     * @return
     */
//    IPage<UpmsUser> getUpmsUserListPage(Page<UpmsUser> pages, @Param(Constants.WRAPPER) Wrapper query);

    /**
     * 角色列表分页
     * @param pages
     * @param upmsRoleQueryWrapper
     * @return
     */
    IPage<UpmsRole> getRoleListPage(Page<UpmsRole> pages, @Param(Constants.WRAPPER) Wrapper<UpmsRole> upmsRoleQueryWrapper, @Param("sortIndex") String sortIndex);

    IPage<UpmsUserRes> getUpmsUserListPage(Page<UpmsUserRes> pages, @Param(Constants.WRAPPER) QueryWrapper<UpmsUser> upmsUserQueryWrapper, @Param("sortIndex") String sortIndex);

    List<UpmsRoleRes> getUpmsRoleResList(@Param("userId") String userId);

    List<PermissionMiniRes> selectAllMiniPermission();

}