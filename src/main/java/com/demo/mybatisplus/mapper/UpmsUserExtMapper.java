package com.demo.mybatisplus.mapper;


import com.demo.mybatisplus.model.UpmsUser;
import com.demo.mybatisplus.model.res.UpmsUserMiniRes;

import java.util.List;

public interface UpmsUserExtMapper {

    UpmsUser selectByUsername(String username);

    List<UpmsUserMiniRes> selectUpmsUserMiniSelectList();


}