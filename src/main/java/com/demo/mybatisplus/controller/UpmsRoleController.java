package com.demo.mybatisplus.controller;

import com.demo.mybatisplus.model.UpmsRole;
import com.demo.mybatisplus.model.dto.UpmsRoleAddDto;
import com.demo.mybatisplus.model.dto.UpmsRoleListDto;
import com.demo.mybatisplus.model.dto.UpmsUserPermissionListDto;
import com.demo.mybatisplus.model.vo.ResultVO;
import com.demo.mybatisplus.service.UpmsPermissionManagerService;
import com.demo.mybatisplus.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author zhangfeng
 * @date 2020/4/14 10:56 上午
 */
@Api(tags = "角色管理")
@RequestMapping("/upmsRoles")
@RestController
public class UpmsRoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsRoleController.class);

    @Autowired
    private UpmsPermissionManagerService upmsPermissionManagerService;
    /**
     * 角色列表
     */
    @ApiOperation(value = "角色列表", response = UpmsRole.class)
    @PostMapping("/getUpmsRoleList")
    public ResultVO getUpmsRoleList(@RequestBody UpmsRoleListDto upmsRoleListDto) {
        String loggerF = "角色列表";
        LOGGER.info("[{}] -E 开始，参数 [{}]", loggerF, JsonUtil.beanToJson(upmsRoleListDto));
        return upmsPermissionManagerService.getUserRoleList(upmsRoleListDto);
    }

    /**
     * 添加/编辑角色
     */
    @ApiOperation("添加/编辑角色")
    @PostMapping("/addUpmsRole")
    public ResultVO insertUpmsRole(@RequestBody @Valid UpmsRoleAddDto dto, BindingResult bindingResult) {
        String loggerF = "添加/编辑角色";
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        LOGGER.info("[{}] -E 开始，参数 [{}]", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = upmsPermissionManagerService.insertRole(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    /**
     * 删除角色
     */
    @ApiOperation("删除角色")
    @GetMapping("/removeUpmsRole/{roleId}")
    public ResultVO removeUpmsRole(@PathVariable String roleId) {
        String loggerF = "删除角色";
        LOGGER.info("[{}] -E 开始，参数roleId=[{}] ", loggerF, roleId);
        ResultVO resultVO = upmsPermissionManagerService.removeRole(roleId);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    /**
     * 该接口只包含角色的权限
     */
    @ApiOperation("获取角色的权限列表")
    @GetMapping("/getPermissionListByRoleId/{roleId}")
    public ResultVO getPermissionListByRoleId(@PathVariable String roleId) {
        String loggerF = "获取角色的权限列表";
        LOGGER.info("[{}] -S 开始，参数 roleId=[{}]", loggerF, roleId);
        ResultVO<Map<String, Object>> resultVO = upmsPermissionManagerService.getUserPermissionListByRoleId(roleId);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    /**
     * 更新角色的权限
     */
    @ApiOperation("更新角色的权限")
    @PostMapping("/updatePermissionListByRoleId")
    public ResultVO updatePermissionListByRoleId(@RequestBody UpmsUserPermissionListDto dto) throws Exception {
        String loggerF = "更新角色的权限";
        LOGGER.info("[{}] -S 开始，参数 dto=[{}]", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = upmsPermissionManagerService.updatePermissionListByRoleId(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

}
