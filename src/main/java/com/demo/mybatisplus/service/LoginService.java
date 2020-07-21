package com.demo.mybatisplus.service;


import com.demo.mybatisplus.model.dto.RefreshTokenDto;
import com.demo.mybatisplus.model.dto.TokenDto;
import com.demo.mybatisplus.model.dto.UserDto;
import com.demo.mybatisplus.model.vo.ResultVO;

/**
 * @author zhangfeng
 * @date 2020/4/14 3:52 下午
 */
public interface LoginService {
    ResultVO login(UserDto dto);

    ResultVO getUserPermissionTree();

    ResultVO refreshToken(RefreshTokenDto dto);

    ResultVO logout();

    ResultVO getUserInfo();

    ResultVO validToken(TokenDto dto);
}
