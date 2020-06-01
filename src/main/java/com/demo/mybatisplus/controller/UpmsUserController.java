package com.demo.mybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mybatisplus.mapper.UpmsUserExtMapper;
import com.demo.mybatisplus.model.UpmsUser;
import com.demo.mybatisplus.service.IUpmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author Zhangfeng
 * @since 2020-06-01
 */
@Controller
@RequestMapping("/upmsUser")
@Api(tags = "upmsUser")
public class UpmsUserController {

    @Autowired
    IUpmsUserService iUpmsUserService;

    @Autowired
    UpmsUserExtMapper upmsUserExtMapper;

    @GetMapping(value = "/queryById/{id}")
    @ApiOperation("根据id查询")
    @ResponseBody
    public Object queryById(@PathVariable String id) {
        UpmsUser upmsUser = iUpmsUserService.getById(id);
        return upmsUser;
    }

    @GetMapping(value = "/queryAll")
    @ApiOperation("查询所有")
    @ResponseBody
    public Object queryAll() {
        List<UpmsUser> upmsUserList = upmsUserExtMapper.selectAll();
        return upmsUserList;
    }

    @GetMapping(value = "/queryAllPage/{page}/{size}")
    @ApiOperation("查询所有分页")
    @ResponseBody
    public Object queryAllPage(@PathVariable Integer page, @PathVariable Integer size) {
        Page<UpmsUser> pages = new Page<>(page, size);
        IPage<UpmsUser> userIPage = iUpmsUserService.pageMaps(pages, new QueryWrapper<UpmsUser>());
        List<UpmsUser> upmsUserList = upmsUserExtMapper.selectAll();
        return upmsUserList;
    }

}

