package com.demo.mybatisplus.controller;

import com.demo.mybatisplus.annotation.MethodPerformanceLog;
import com.demo.mybatisplus.model.dto.RefreshTokenDto;
import com.demo.mybatisplus.model.dto.TokenDto;
import com.demo.mybatisplus.model.dto.UserDto;
import com.demo.mybatisplus.model.vo.ResultVO;
import com.demo.mybatisplus.service.LoginService;
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

/**
 * @author zhangfeng
 * @date 2020/4/14 3:50 下午
 */
@Api(value = "登陆",tags = "登陆")
@RequestMapping("/login")
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @ApiOperation("登陆")
    @PostMapping("/login")
    @MethodPerformanceLog(desc = "登陆")
    public ResultVO login(@RequestBody @Valid UserDto dto, BindingResult bindingResult) {
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        String loggerF = "登陆";
        LOGGER.info("[{}] -S 开始，参数 [{}]", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = loginService.login(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("退出")
    @PostMapping("/logout")
    @MethodPerformanceLog(desc = "退出")
    public ResultVO logout() {
        String loggerF = "退出";
        LOGGER.info("[{}] -S 开始，参数 [{}]", loggerF);
        ResultVO resultVO = loginService.logout();
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF);
        return resultVO;
    }

    @ApiOperation("根据用户名和refreshToken获取token")
    @PostMapping("/refreshToken")
    @MethodPerformanceLog(desc = "根据用户名和refreshToken获取token")
    public ResultVO refreshToken(@RequestBody @Valid RefreshTokenDto dto, BindingResult bindingResult) {
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        String loggerF = "刷新token";
        LOGGER.info("[{}] -S 开始，参数 [{}]", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = loginService.refreshToken(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("获取用户的所有的权限")
    @GetMapping("/getUserPermissionTree")
    @MethodPerformanceLog(desc = "获取用户的所有的权限")
    public ResultVO getUserPermissionTree() {
        String loggerF = "获取登陆用户的所有的权限";
        LOGGER.info("[{}] -S 开始，参数 [{}]", loggerF);
        ResultVO resultVO = loginService.getUserPermissionTree();
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("查询用户的基础信息")
    @GetMapping("/getUserInfo")
    @MethodPerformanceLog(desc = "查询用户的基础信息")
    public ResultVO getUserInfo() {
        String loggerF = "查询用户的基础信息";
        LOGGER.info("[{}] -S 开始，参数 [{}]", loggerF);
        ResultVO resultVO = loginService.getUserInfo();
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("验证token是否过期")
    @PostMapping("/validToken")
    @MethodPerformanceLog(desc = "验证token是否过期")
    public ResultVO validToken(@RequestBody @Valid TokenDto dto, BindingResult bindingResult) {
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        String loggerF = "验证token是否过期";
        LOGGER.info("[{}] -S 开始，参数 [{}]", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = loginService.validToken(dto);
        LOGGER.info("[{}] -E 结束，返回值 [{}]", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

}
