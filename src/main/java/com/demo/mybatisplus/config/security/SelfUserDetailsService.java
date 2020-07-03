package com.demo.mybatisplus.config.security;

import com.demo.mybatisplus.mapper.UpmsPermissionExtMapper;
import com.demo.mybatisplus.mapper.UpmsUserExtMapper;
import com.demo.mybatisplus.model.UpmsPermission;
import com.demo.mybatisplus.model.UpmsRole;
import com.demo.mybatisplus.model.UpmsUser;
import com.demo.mybatisplus.utils.jwt.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private UpmsUserExtMapper upmsUserExtMapper;

    @Autowired
    private UpmsPermissionExtMapper upmsPermissionExtMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UpmsUser upmsUser = upmsUserExtMapper.selectByUsername(username);
        if (null == upmsUser) {
            throw new UsernameNotFoundException(username);
        }
        if (upmsUser.getLocked()) {
            throw new AccountExpiredException("账号被锁定");
        }
        // 查询权限
        List<UpmsPermission> upmsPermissionList = upmsPermissionExtMapper.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(upmsPermissionList)) {
            for (UpmsPermission upmsPermission : upmsPermissionList) {
                authorityList.add(new SimpleGrantedAuthority(upmsPermission.getPermissionValue()));
            }
        }
        // 查询角色
        List<UpmsRole> upmsRoleList = upmsPermissionExtMapper.selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
        List<String> rolesList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(upmsRoleList)) {
            for (UpmsRole upmsRole: upmsRoleList) {
                rolesList.add(upmsRole.getName());
            }
        }
        JwtUserDetails jwtUserDetails = new JwtUserDetails(
                upmsUser.getUserId(),
                upmsUser.getUsername(),
                new BCryptPasswordEncoder().encode(upmsUser.getPassword()),
                authorityList,
                rolesList);

        return jwtUserDetails;
    }
}
