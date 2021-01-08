package com.demo.mybatisplus.controller;


import com.demo.mybatisplus.model.res.ConfigGlobalSelectRes;
import com.demo.mybatisplus.model.vo.ResultVO;
import com.demo.mybatisplus.service.IConfigGlobalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 全局基础字典 前端控制器
 * </p>
 *
 * @author Zhangfeng
 * @since 2021-01-03
 */
@RestController
@Api(tags = "公共下拉选接口")
@RequestMapping("/configGlobal")
public class ConfigGlobalController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigGlobalController.class);

    @Autowired
    private IConfigGlobalService configGlobalService;

    @GetMapping("/queryCommonSelectList/{parentCd}")
    @ApiOperation(value = "根据parentCd 查询所有的下拉", response = ConfigGlobalSelectRes.class)
    public ResultVO queryCommonSelectList(@PathVariable String parentCd) {
        List<ConfigGlobalSelectRes> list = configGlobalService.queryCommonSelectList(parentCd);
        return ResultVO.successList(list);
    }
}

