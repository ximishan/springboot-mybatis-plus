package com.demo.mybatisplus.controller;

import com.demo.mybatisplus.enums.ResultCode;
import com.demo.mybatisplus.model.UpmsPermission;
import com.demo.mybatisplus.model.dto.PermissionDto;
import com.demo.mybatisplus.model.dto.UpmsRoleListDto;
import com.demo.mybatisplus.model.vo.ResultVO;
import com.demo.mybatisplus.service.UpmsPermissionManagerService;
import com.demo.mybatisplus.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author zhangfeng
 * @date 2020/4/14 10:56 上午
 */
@Api(tags = "权限")
@RequestMapping("/permission")
@RestController
@PreAuthorize("hasAuthority('upms:permission')")
public class PermissionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private UpmsPermissionManagerService upmsPermissionManagerService;


    @ApiOperation("添加权限")
    @PostMapping("/addPermission")
    public ResultVO addPermission(@RequestBody @Valid PermissionDto dto, BindingResult bindingResult) {
        String loggerF = "添加权限";
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        LOGGER.info("[{}] -E 开始，参数 [{}]", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = upmsPermissionManagerService.addPermission(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("更新权限")
    @PostMapping("/updatePermission")
    public ResultVO updatePermission(@RequestBody @Valid PermissionDto dto, BindingResult bindingResult) {
        String loggerF = "更新权限";
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        LOGGER.info("[{}] -E 开始，参数 [{}]", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = upmsPermissionManagerService.updatePermission(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    /**
     * 权限列表
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "权限列表", response = UpmsPermission.class)
    @PostMapping("/getPermissionList")
    public ResultVO getPermissionList(@RequestBody UpmsRoleListDto dto) {
        String loggerF = "权限列表";
        LOGGER.info("[{}] -S 开始，参数 userId=[{}]", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = upmsPermissionManagerService.getAllPermissionList(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("删除权限")
    @PostMapping("/removeUpmsPermission/{permissionId}")
    public ResultVO removeUpmsPermission(@PathVariable String permissionId) {
        String loggerF = "删除权限";
        LOGGER.info("[{}] -S 开始，参数 userId=[{}]", loggerF, permissionId);
        if (StringUtils.isBlank(permissionId)) {
            return new ResultVO(ResultCode.PERMISSION_NOT_EXIST_FAILED);
        }
        ResultVO<Map<String, Object>> resultVO = upmsPermissionManagerService.removePermissionById(permissionId);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

}
