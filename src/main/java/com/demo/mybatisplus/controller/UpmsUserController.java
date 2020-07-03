package com.demo.mybatisplus.controller;

import com.demo.mybatisplus.annotation.MethodPerformanceLog;
import com.demo.mybatisplus.model.UpmsUser;
import com.demo.mybatisplus.model.dto.*;
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
@Api(tags = "用户管理")
@RequestMapping("/upmsUser")
@RestController
public class UpmsUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserController.class);

    @Autowired
    private UpmsPermissionManagerService upmsPermissionManagerService;

    /**
     * 重新密码
     */
    @ApiOperation("给用户重制密码")
    @GetMapping("/resetPassword/{userId}")
    @MethodPerformanceLog(desc = "给用户重制密码")
    public ResultVO resetPassword(@PathVariable String userId) {
        String loggerF = "给用户重制密码";
        LOGGER.info("[{}] -S 开始，参数 userId=[{}]", loggerF);
        ResultVO resultVO = upmsPermissionManagerService.resetPassword(userId);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    /**
     * 更新状态
     */
    @ApiOperation("更新用户状态")
    @GetMapping("/updateUserLock/{userId}")
    @MethodPerformanceLog(desc = "更新用户状态")
    public ResultVO updateUserLock(@PathVariable String userId) {
        String loggerF = "更新用户状态";
        LOGGER.info("[{}] -S 开始，参数 userId=[{}]", loggerF);
        ResultVO resultVO = upmsPermissionManagerService.updateUserLock(userId);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }


    /**
     * 该接口只包含用户的权限
     * @throws Exception
     */
    @ApiOperation("获取用户的权限列表")
    @GetMapping("/getPermissionListByUserId/{userId}")
    public ResultVO getUserPermissionListByUserId(@PathVariable String userId) {
        String loggerF = "获取用户的权限列表,包含用户权限";
        LOGGER.info("[{}] -S 开始，参数 userId=[{}]", loggerF);

        ResultVO resultVO = upmsPermissionManagerService.getUserPermissionListByUserId(userId);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    /**
     * 添加用户信息
     * @return
     * @throws Exception
     */
    @ApiOperation("添加用户信息")
    @PostMapping("/addUpmsUser")
    @MethodPerformanceLog(desc = "添加用户信息")
    public ResultVO addUpmsUser(@RequestBody @Valid UserRegisterDto dto, BindingResult bindingResult) {
        String loggerF = "添加用户信息";
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        LOGGER.info("[{}] -S 开始，参数 userId=[{}]", loggerF);
        ResultVO<Map<String, Object>> resultVO = upmsPermissionManagerService.addUpmsUser(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }


    /**
     * 删除用户信息
     */
    @ApiOperation("删除用户信息")
    @GetMapping("/removeUpmsUser/{userId}")
    @MethodPerformanceLog(desc = "删除用户信息")
    public ResultVO removeUpmsUser(@PathVariable String userId) {
        String loggerF = "删除用户信息";
        LOGGER.info("[{}] -S userId=[{}]",loggerF,userId);
        ResultVO<Map<String, Object>> resultVO = upmsPermissionManagerService.removeUpmsUser(userId);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }


    /**
     * 编辑用户信息
     */
    @ApiOperation("编辑用户信息")
    @PostMapping("/updateUpmsUser")
    @MethodPerformanceLog(desc = "编辑用户信息")
    public ResultVO updateUpmsUser(@RequestBody @Valid UpmsUserUpdateDto dto, BindingResult bindingResult) {
        String loggerF = "编辑用户信息";
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        LOGGER.info("[{}] -S 开始，参数 dto=[{}]", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = upmsPermissionManagerService.updateUpmsUser(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    /**
     * 更新用户的权限
     */
    @ApiOperation("更新用户的权限")
    @PostMapping("/updatePermissionListByUserId")
    @MethodPerformanceLog(desc = "更新用户的权限")
    public ResultVO setPermissionListByUserId(@RequestBody UpmsUserPermissionListDto dto){
        String loggerF = "获取用户的权限列表,更新用户的权限";
        LOGGER.info("[{}] -S 开始，参数 userId=[{}], dto=[{}]", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = upmsPermissionManagerService.updatePermissionListByUserId(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    /**
     * 用户列表
     */
    @ApiOperation(value = "用户列表",response = UpmsUser.class)
    @PostMapping("/getUpmsUserList")
    public ResultVO getAllUpmsUserList(@RequestBody UpmsRoleListDto upmsRoleListDto){
        String loggerF = "用户列表";
        LOGGER.info("[{}] -E 开始，参数 [{}]", loggerF, JsonUtil.beanToJson(upmsRoleListDto));
        ResultVO resultVO = upmsPermissionManagerService.getUpmsUserList(upmsRoleListDto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("查询用户的角色")
    @GetMapping("/getUpmsUserRole/{userId}")
    public ResultVO getUpmsUserRole(@PathVariable String userId) {
        String loggerF = "查询用户的角色";
        LOGGER.info("[{}] -E 开始，参数 [{}]", loggerF, userId);
        ResultVO resultVO = upmsPermissionManagerService.findUpmsUserRole(userId);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("给用户分配角色")
    @PostMapping("/addUpmsUserRole")
    @MethodPerformanceLog(desc = "给用户分配角色")
    public ResultVO updateUpmsUserRole(@RequestBody UpmsUserRolesDto upmsUserRolesDto) {
        String loggerF = "给用户分配角色";
        LOGGER.info("[{}] -E 开始，参数 [{}]", loggerF, JsonUtil.beanToJson(upmsUserRolesDto));
        ResultVO resultVO = upmsPermissionManagerService.updateUpmsUserRoles(upmsUserRolesDto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("所有的用户列表下拉")
    @PostMapping("/getUpmsUserSelectList")
    @MethodPerformanceLog(desc = "所有的用户列表下拉")
    public ResultVO getUpmsUserSelectList() {
        String loggerF = "所有的用户列表下拉";
        LOGGER.info("[{}] -E 开始", loggerF);
        ResultVO resultVO = upmsPermissionManagerService.getUpmsUserSelectList();
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

}
