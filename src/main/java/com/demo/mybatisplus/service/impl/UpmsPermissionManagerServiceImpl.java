package com.demo.mybatisplus.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mybatisplus.constants.ApiConstant;
import com.demo.mybatisplus.enums.ResultCode;
import com.demo.mybatisplus.mapper.*;
import com.demo.mybatisplus.model.*;
import com.demo.mybatisplus.model.dto.*;
import com.demo.mybatisplus.model.res.PermissionMiniRes;
import com.demo.mybatisplus.model.res.UpmsRoleRes;
import com.demo.mybatisplus.model.res.UpmsUserMiniRes;
import com.demo.mybatisplus.model.res.UpmsUserRes;
import com.demo.mybatisplus.model.vo.PageVo;
import com.demo.mybatisplus.model.vo.ResultVO;
import com.demo.mybatisplus.service.IUpmsRoleService;
import com.demo.mybatisplus.service.UpmsPermissionManagerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangfeng
 * @date 2020/4/14 11:21 上午
 */
@Service
@Transactional
public class UpmsPermissionManagerServiceImpl implements UpmsPermissionManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsPermissionManagerServiceImpl.class);

    @Autowired
    private UpmsPermissionExtMapper upmsPermissionExtMapper;

    @Autowired
    private UpmsPermissionMapper upmsPermissionMapper;

    @Autowired
    private UpmsRoleMapper upmsRoleMapper;

    @Autowired
    private UpmsUserMapper upmsUserMapper;

    @Autowired
    private IUpmsRoleService upmsRoleService;

    @Autowired
    private UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Autowired
    private UpmsUserPermissionMapper upmsUserPermissionMapper;

    @Autowired
    private UpmsUserRoleMapper upmsUserRoleMapper;

    @Autowired
    private UpmsUserExtMapper upmsUserExtMapper;

    /**
     * 自定义sql排序，使用mybatis-plus 一直失败，使用传参的方式。
     * @param dto
     * @return
     */
    @Override
    public ResultVO getUserRoleList(UpmsRoleListDto dto) {
        Page<UpmsRole> pages = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        QueryWrapper<UpmsRole> upmsRoleQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getSearch())) {
            upmsRoleQueryWrapper.like("name", dto.getSearch());
        }
        IPage<UpmsRole> upmsUserIPage = upmsPermissionExtMapper.getRoleListPage(pages, upmsRoleQueryWrapper, dto.getSortIndex());
        return ResultVO.successData(upmsUserIPage);
    }

    @Override
    public ResultVO insertRole(UpmsRoleAddDto dto) {
        long count = upmsRoleService.countRoleNameNotEqRoleId(dto.getName(), null);
        if (StringUtils.isNotBlank(dto.getRoleId())) {
            UpmsRole role = upmsRoleMapper.selectById(dto.getRoleId());
            if (role != null ) {
                if (!role.getName().equals(dto.getName())) {
                    //校验数据库中是否存在相同的角色
                    count = upmsRoleService.countRoleNameNotEqRoleId(dto.getName(), dto.getRoleId());
                    if (count > 0) {
                        return new ResultVO(ResultCode.ROLE_EXIST_FAILED);
                    }
                }
            } else {
                return new ResultVO(ResultCode.ROLE_NOT_EXIST_FAILED);
            }
            //做更新
            UpmsRole upmsRole = new UpmsRole();
            upmsRole.setRoleId(dto.getRoleId());
            upmsRole.setDescription(dto.getDescription());
            upmsRole.setName(dto.getName());
            upmsRole.setTitle(dto.getTitle());
            upmsRoleMapper.updateById(upmsRole);
        } else {
            //判断roleName 是否存在
            if (count > 0) {
                return new ResultVO(ResultCode.ROLE_EXIST_FAILED);
            }
            UpmsRole upmsRole = new UpmsRole();
            upmsRole.setDescription(dto.getDescription());
            upmsRole.setName(dto.getName());
            upmsRole.setTitle(dto.getTitle());
            long time = System.currentTimeMillis();
            upmsRole.setRoleId(IdUtil.fastSimpleUUID());
            upmsRole.setCtime(time);
            upmsRole.setOrders(time);
            upmsRoleMapper.insert(upmsRole);
        }
        return ResultVO.success("操作成功");
    }

    @Override
    public ResultVO removeRole(String roleId) {
        upmsRoleMapper.deleteById(roleId);
        // 删除角色对应的权限

        // 删除角色跟用户的关联关系

        return ResultVO.success();
    }

    @Override
    public ResultVO getAllPermissionList(UpmsRoleListDto dto) {
        Map map = new HashMap();
        if (StringUtils.isNotBlank(dto.getSearch())) {
            map.put("search",dto.getSearch());
        }
        int total = upmsPermissionExtMapper.countPermissionList(map);
        List<UpmsPermission> list = new ArrayList<>();
        PageVo pageVo = new PageVo(dto.getCurrentPage(), total, dto.getPageSize());
        if (total > 0) {
            if (!StringUtils.isBlank(dto.getSort())  && !StringUtils.isBlank(dto.getOrder())) {
                map.put("sortIndex",dto.getOrder() +" "+dto.getSort());
            }
            map.put("startIndex", pageVo.getStartRecord());
            map.put("limit", pageVo.getPageSize());
            list = upmsPermissionExtMapper.getPermissionList(map);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("records", list);
        result.put("total", total);
        return ResultVO.successData(result);
    }

    @Override
    public ResultVO removePermissionById(String permissionId) {
        String info = "删除权限";
        LOGGER.info("[{}] -S  permissionId=[{}]",info,permissionId);
        // 删除当前权限
        QueryWrapper<UpmsPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission_id", permissionId);
        upmsPermissionMapper.delete(queryWrapper);
        // 删除当前权限的子权限

        // 删除角色对应的权限
        upmsPermissionExtMapper.deleteRolePermissionByPermissionId(permissionId);
        // 删除用户对应的权限
        upmsPermissionExtMapper.deleteUserPermissionByPermissionId(permissionId);
        return ResultVO.success();
    }

    @Override
    public ResultVO addPermission(PermissionDto dto) {
        UpmsPermission upmsPermission = new UpmsPermission();
        String permissionId = IdUtil.simpleUUID();
        upmsPermission.setPermissionId(permissionId);
        upmsPermission.setPid(dto.getPid());
        upmsPermission.setName(dto.getName());
        upmsPermission.setType(dto.getType());
        upmsPermission.setPermissionValue(dto.getPermissionValue());
        upmsPermission.setUri(dto.getUri());
        upmsPermission.setIcon(dto.getIcon());
        upmsPermission.setStatus(dto.getStatus());
        long times = System.currentTimeMillis();
        upmsPermission.setCtime(times);
        int maxOrders = upmsPermissionExtMapper.countMaxOrders();
        upmsPermission.setOrders(Long.valueOf(maxOrders+1));
        upmsPermissionMapper.insert(upmsPermission);
        return ResultVO.success();
    }

    @Override
    public ResultVO updatePermission(PermissionDto dto) {
        UpmsPermission upmsPermission = new UpmsPermission();
        upmsPermission.setPermissionId(dto.getPermissionId());
        upmsPermission.setPid(dto.getPid());
        upmsPermission.setName(dto.getName());
        upmsPermission.setType(dto.getType());
        upmsPermission.setPermissionValue(dto.getPermissionValue());
        upmsPermission.setUri(dto.getUri());
        upmsPermission.setIcon(dto.getIcon());
        upmsPermission.setStatus(dto.getStatus());
        upmsPermissionMapper.updateById(upmsPermission);
        return ResultVO.success();
    }

    @Override
    public ResultVO<Map<String, Object>> getUserPermissionListByRoleId(String roleId) {
        // 角色已有权限
        List<UpmsRolePermission> rolePermissions = selectUpmsRolePermisstionByUpmsRoleId(roleId);
        List<String> permissionIds = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(rolePermissions)) {
            rolePermissions.stream().forEach(item -> {
                permissionIds.add(item.getPermissionId());
            });
        }
        List<PermissionMiniRes> permissionMiniResList = upmsPermissionExtMapper.selectAllMiniPermission();
        // 所有的权限
        List<PermissionMiniRes> folder =
                permissionMiniResList.stream()
                        .filter(permissionRes -> "0".equals(permissionRes.getPid()))
                        .map(permissionRes -> {
                            permissionRes.setChildren(getChildrens(permissionRes, permissionMiniResList));
                            return permissionRes;
                        })
                        .collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        result.put("all", folder);
        result.put("hasList", permissionIds);
        return ResultVO.successData(result);
    }

    private List<PermissionMiniRes> getChildrens(PermissionMiniRes root, List<PermissionMiniRes> all) {

        List<PermissionMiniRes> childrens = all.stream()
                .filter(permissionRes -> permissionRes.getPid().equals(root.getPermissionId()))
                .map(permissionRes -> {
                    permissionRes.setChildren(getChildrens(permissionRes, all));
                    return permissionRes;
                })
                .collect(Collectors.toList());
        return childrens;
    }

    /**
     * 根据角色id获取所拥有的权限
     * @param upmsRoleId
     * @return
     */
    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(String upmsRoleId) {
        QueryWrapper<UpmsRolePermission> upmsRolePermissionQueryWrapper = new QueryWrapper<>();
        upmsRolePermissionQueryWrapper.eq("role_id", upmsRoleId);
        List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionMapper.selectList(upmsRolePermissionQueryWrapper);
        return upmsRolePermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(String upmsUserId) {
        QueryWrapper<UpmsUserPermission> upmsUserPermissionQueryWrapper = new QueryWrapper<>();
        upmsUserPermissionQueryWrapper.eq("user_id", upmsUserId);
        List<UpmsUserPermission> upmsUserPermissions = upmsUserPermissionMapper.selectList(upmsUserPermissionQueryWrapper);
        return upmsUserPermissions;
    }

    @Override
    public ResultVO updatePermissionListByRoleId(UpmsUserPermissionListDto dto) {
        String roleId = dto.getUserId();
        // 首先删除
        upmsPermissionExtMapper.deleteRolePermissionByRoleId(dto.getUserId());
        // 新增权限
        List<String> perIds = dto.getPermissionIdList();
        for (String str:perIds) {
            UpmsRolePermission upmsRolePermission = new UpmsRolePermission();
            upmsRolePermission.setRolePermissionId(IdUtil.fastSimpleUUID());
            upmsRolePermission.setRoleId(roleId);
            upmsRolePermission.setPermissionId(str);
            upmsRolePermission.setCreateDts(new Date());
            upmsRolePermissionMapper.insert(upmsRolePermission);
        }
        return ResultVO.success();
    }

    @Override
    public long countUpmsUserByUsername(String username) {
        QueryWrapper<UpmsUser> upmsUserQueryWrapper = new QueryWrapper<>();
        upmsUserQueryWrapper.eq("username", username);
        long count = upmsUserMapper.selectCount(upmsUserQueryWrapper);
        return count;
    }

    @Override
    public ResultVO<Map<String, Object>> addUpmsUser(UserRegisterDto userRegisterDto) {

        if (countUpmsUserByUsername(userRegisterDto.getUsername()) >0 ) {
            return ResultVO.error(ResultCode.USERNAME_EXIST_FAILED);
        }
        UpmsUser upmsUser = new UpmsUser();
        if (StringUtils.isBlank(userRegisterDto.getPassword())) {
            userRegisterDto.setPassword(ApiConstant.DEFAULT_PASSWORD);
        }
        String userId = IdUtil.fastSimpleUUID();
        upmsUser.setUserId(userId);
        upmsUser.setRealname(userRegisterDto.getRealname());
        upmsUser.setUsername(userRegisterDto.getUsername());
        upmsUser.setLocked(userRegisterDto.isLocked());
        upmsUser.setEmail(userRegisterDto.getEmail());
        upmsUser.setSex(userRegisterDto.getSex());
        upmsUser.setPhone(userRegisterDto.getPhone());
        upmsUser.setPassword(new BCryptPasswordEncoder().encode(userRegisterDto.getPassword()));
        upmsUser.setCreateDts(new Date());
        upmsUserMapper.insert(upmsUser);

        if (CollectionUtils.isNotEmpty(userRegisterDto.getRoleIdList())) {
            for (String roleId: userRegisterDto.getRoleIdList()) {
                UpmsUserRole upmsUserRole = new UpmsUserRole();
                upmsUserRole.setUserId(userId);
                upmsUserRole.setRoleId(roleId);
                upmsUserRole.setCreateDts(new Date());
                upmsUserRole.setUserRoleId(IdUtil.fastSimpleUUID());
                upmsUserRoleMapper.insert(upmsUserRole);
            }
        }
        return ResultVO.success();
    }

    @Override
    public ResultVO getUserPermissionListByUserId(String userId) {
        List<UpmsUserPermission> upmsUserPermissions = selectUpmsUserPermissionByUpmsUserId(userId);

        QueryWrapper<UpmsPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", true)
                .orderByAsc("orders");

        List<UpmsPermission> upmsPermissions = upmsPermissionMapper.selectList(queryWrapper);
        // 目录
        JSONArray folders = new JSONArray();
        for (UpmsPermission upmsPermission: upmsPermissions) {
            if (!"0".equals(upmsPermission.getPid()) || upmsPermission.getType() != 1) {
                continue;
            }
            JSONObject node = new JSONObject();
            node.put("id", upmsPermission.getPermissionId());
            node.put("name", upmsPermission.getName());
            node.put("open", true);
            for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                if (upmsUserPermission.getPermissionId().equals(upmsPermission.getPermissionId())) {
                    node.put("checked", true);
                }
            }
            node.put("checked", node.containsKey("checked")? true: false);
            folders.add(node);
            // 菜单
            JSONArray menus = new JSONArray();
            for (Object folder : folders) {
                for (UpmsPermission upmsPermission2: upmsPermissions) {
                    if (!upmsPermission2.getPid().equals(((JSONObject) folder).getStr("id"))  || upmsPermission2.getType() != 2) {
                        continue;
                    }
                    JSONObject node2 = new JSONObject();
                    node2.put("id", upmsPermission2.getPermissionId());
                    node2.put("name", upmsPermission2.getName());
                    node2.put("open", true);
                    for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                        if (upmsUserPermission.getPermissionId() .equals(upmsPermission2.getPermissionId()) ) {
                            node2.put("checked", true);
                        }
                    }
                    node2.put("checked", node2.containsKey("checked")? true: false);
                    menus.add(node2);
                    // 按钮
                    JSONArray buttons = new JSONArray();
                    for (Object menu : menus) {
                        for (UpmsPermission upmsPermission3: upmsPermissions) {
                            if (!upmsPermission3.getPid().equals(((JSONObject) menu).getStr("id")) || upmsPermission3.getType() != 3) {
                                continue;
                            }
                            JSONObject node3 = new JSONObject();
                            node3.put("id", upmsPermission3.getPermissionId());
                            node3.put("name", upmsPermission3.getName());
                            node3.put("open", true);
                            for (UpmsUserPermission upmsUserPermission : upmsUserPermissions) {
                                if (upmsUserPermission.getPermissionId() .equals(upmsPermission3.getPermissionId()) ) {
                                    node3.put("checked", true);
                                }
                            }
                            node3.put("checked", node3.containsKey("checked")? true: false);
                            buttons.add(node3);
                        }
                        if (buttons.size() > 0) {
                            ((JSONObject) menu).put("children", buttons);
                            buttons = new JSONArray();
                        }
                    }
                }
                if (menus.size() > 0) {
                    ((JSONObject) folder).put("children", menus);
                    menus = new JSONArray();
                }
            }
        }
        return ResultVO.successData(folders);
    }

    @Override
    public ResultVO removeUpmsUser(String userId) {
        upmsUserMapper.deleteById(userId);
        // 删除用户角色关系
        upmsPermissionExtMapper.deleteUserRoleByUserId(userId);
        // 删除用户权限关系
        upmsPermissionExtMapper.deleteUserPermissionByUserId(userId);
        return ResultVO.success();
    }

    @Override
    public ResultVO updateUpmsUser(UpmsUserUpdateDto dto) {
        UpmsUser upmsUser = new UpmsUser();
        upmsUser.setRealname(dto.getRealname());
        upmsUser.setLocked(dto.isLocked());
        upmsUser.setEmail(dto.getEmail());
        upmsUser.setSex(dto.getSex());
        upmsUser.setPhone(dto.getPhone());
        upmsUser.setUserId(dto.getUserId());
        upmsUser.setUpdateDts(new Date());
        upmsUserMapper.updateById(upmsUser);

        // 删除之前的角色
        QueryWrapper<UpmsUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", dto.getUserId());
        upmsUserRoleMapper.delete(queryWrapper);

        if (CollectionUtils.isNotEmpty(dto.getRoleIdList())) {
            for (String roleId: dto.getRoleIdList()) {
                UpmsUserRole upmsUserRole = new UpmsUserRole();
                upmsUserRole.setUserId(dto.getUserId());
                upmsUserRole.setRoleId(roleId);
                upmsUserRole.setCreateDts(new Date());
                upmsUserRole.setUserRoleId(IdUtil.fastSimpleUUID());
                upmsUserRoleMapper.insert(upmsUserRole);
            }
        }
        return ResultVO.success();
    }

    @Override
    public ResultVO<Map<String, Object>> updatePermissionListByUserId(UpmsUserPermissionListDto dto) {
        String userId = dto.getUserId();
        upmsPermissionExtMapper.deleteUserPermissionByUserId(userId);
        List<String> perIds = dto.getPermissionIdList();
        for (String str: perIds) {
            UpmsUserPermission upmsUserPermission = new UpmsUserPermission();
            upmsUserPermission.setUserPermissionId(IdUtil.fastSimpleUUID());
            upmsUserPermission.setUserId(userId);
            upmsUserPermission.setPermissionId(str);
            upmsUserPermission.setCreateDts(new Date());
            upmsUserPermissionMapper.insert(upmsUserPermission);
        }
        return ResultVO.success();
    }

    /**
     * mybatis-plus orderBy 非自定义的sql使用没问题，
     * @param dto
     * @return
     */
    @Override
    public ResultVO getUpmsUserList(UpmsRoleListDto dto) {
        Page<UpmsUserRes> pages = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        QueryWrapper<UpmsUser> upmsUserQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getSearch())) {
            upmsUserQueryWrapper.like("username",dto.getSearch())
                    .or().like("phone", dto.getSearch());
        }
        IPage<UpmsUserRes> upmsUserIPage = upmsPermissionExtMapper.getUpmsUserListPage(pages, upmsUserQueryWrapper, dto.getSortIndex());
        if (CollectionUtils.isNotEmpty(upmsUserIPage.getRecords())) {
            for (UpmsUserRes upmsUserRes: upmsUserIPage.getRecords()) {
                String userId = upmsUserRes.getUserId();
                List<UpmsRoleRes> upmsRoleResList = upmsPermissionExtMapper.getUpmsRoleResList(userId);
                List<String> roleIdList = new ArrayList<>();
                List<String> roleNameList = new ArrayList<>();
                String roleNameStr = null;
                upmsRoleResList.stream().forEach(item -> {
                    roleIdList.add(item.getRoleId());
                    roleNameList.add(item.getTitle());
                });
                upmsUserRes.setRoleIdList(roleIdList);
                roleNameStr = Strings.join(roleNameList, ',');
                upmsUserRes.setRoleNameStr(roleNameStr);
            }
        }
        return ResultVO.successData(upmsUserIPage);
    }

    @Override
    public ResultVO findUpmsUserRole(String userId) {
        QueryWrapper<UpmsRole> queryWrapper = new QueryWrapper<>();
        // 所有角色
        List<UpmsRole> upmsRoles = upmsRoleMapper.selectList(queryWrapper);
        //用户的角色
        List<UpmsRole> userRoles = upmsPermissionExtMapper.selectUpmsRoleByUpmsUserId(userId);
        Map map = new HashMap();
        map.put("allRoles",upmsRoles);
        map.put("userRoles",userRoles);
        return ResultVO.successData(map);
    }

    @Override
    public ResultVO updateUpmsUserRoles(UpmsUserRolesDto dto) {
        String userId = dto.getUserId();
        upmsPermissionExtMapper.deleteUserRoleByUserId(userId);
        // 增加新记录
        if (null != dto.getRoleIds()) {
            for (String roleId : dto.getRoleIds()) {
                if (StringUtils.isBlank(roleId)) {
                    continue;
                }
                UpmsUserRole upmsUserRole = new UpmsUserRole();
                upmsUserRole.setUserRoleId(IdUtil.fastSimpleUUID());
                upmsUserRole.setUserId(dto.getUserId());
                upmsUserRole.setRoleId(roleId);
                upmsUserRole.setCreateDts(new Date());
                upmsUserRoleMapper.insert(upmsUserRole);
            }
        }
        return ResultVO.success();
    }

    @Override
    public ResultVO resetPassword(String userId) {
        UpmsUser upmsUser = new UpmsUser();
        upmsUser.setUserId(userId);
        upmsUser.setPassword(new BCryptPasswordEncoder().encode(ApiConstant.DEFAULT_PASSWORD));
        int rec = upmsUserMapper.updateById(upmsUser);
        LOGGER.info("给userId=[{}] 重制密码结果=[{}]", userId, rec);
        return ResultVO.success();
    }

    @Override
    public ResultVO updateUserLock(String userId) {
        UpmsUser upmsUser = upmsUserMapper.selectById(userId);
        upmsUser.setLocked(upmsUser.getLocked() ? false: true);
        upmsUser.setUserId(userId);
        int rec = upmsUserMapper.updateById(upmsUser);
        LOGGER.info("给userId=[{}] 更新状态=[{}]", userId, rec);
        return ResultVO.success();
    }

    @Override
    public ResultVO getUpmsUserSelectList() {
        List<UpmsUserMiniRes> list = upmsUserExtMapper.selectUpmsUserMiniSelectList();
        return ResultVO.successList(list);
    }

}
