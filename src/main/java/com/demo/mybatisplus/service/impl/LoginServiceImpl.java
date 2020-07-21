package com.demo.mybatisplus.service.impl;

import cn.hutool.core.util.IdUtil;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.demo.mybatisplus.config.security.SelfUserDetailsService;
import com.demo.mybatisplus.constants.RedisConstant;
import com.demo.mybatisplus.enums.ResultCode;
import com.demo.mybatisplus.exception.AuthenticationException;
import com.demo.mybatisplus.exception.LockedAccountException;
import com.demo.mybatisplus.mapper.UpmsPermissionExtMapper;
import com.demo.mybatisplus.mapper.UpmsUserExtMapper;
import com.demo.mybatisplus.model.UpmsRole;
import com.demo.mybatisplus.model.UpmsUser;
import com.demo.mybatisplus.model.dto.RefreshTokenDto;
import com.demo.mybatisplus.model.dto.TokenDto;
import com.demo.mybatisplus.model.dto.UserDto;
import com.demo.mybatisplus.model.res.PermissionRes;
import com.demo.mybatisplus.model.res.UpmsUserRes;
import com.demo.mybatisplus.model.vo.ResultVO;
import com.demo.mybatisplus.service.LoginService;
import com.demo.mybatisplus.service.RedisService;
import com.demo.mybatisplus.utils.jwt.JwtTokenUtil;
import com.demo.mybatisplus.utils.jwt.JwtUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangfeng
 * @date 2020/4/14 3:52 下午
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UpmsUserExtMapper upmsUserExtMapper;

    @Autowired
    private UpmsPermissionExtMapper upmsPermissionExtMapper;

    @Autowired
    private SelfUserDetailsService selfUserDetailsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private HttpServletRequest request;

    @Value("${jwt.refreshTokenExpire}")
    private Long refreshTokenExpire;

    private static AuthenticationManager authenticationManager = new SimpleAuthenticationManager();


    @Override
    public ResultVO login(UserDto dto) {
        // 验证账号密码
        authenticate(dto.getUsername(), dto.getPassword());
        // 生成token 和 refreshToke
        JwtUserDetails userDetails = (JwtUserDetails)selfUserDetailsService.loadUserByUsername(dto.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);

        String refreshToken = IdUtil.fastSimpleUUID();
        Map result = new HashMap<>();
        result.put("token", token);
        result.put("refreshToken", refreshToken);

        redisService.set(RedisConstant.PRE_REFRESH_TOKEN+dto.getUsername(), refreshToken, refreshTokenExpire);
        return ResultVO.successData(result);
    }

    @Override
    public ResultVO getUserPermissionTree() {
        // 查询出所有的菜单
        String userId = request.getAttribute("userId").toString();
        List<PermissionRes> permissionResList = upmsPermissionExtMapper.selectPermissionResListByUpmsUserId(userId);
        // 组装成父子结构
        // 1。首先查询出所有的pid=0 的
        List<PermissionRes> folder =
                permissionResList.stream()
                        .filter(permissionRes -> "0".equals(permissionRes.getPid()))
                        .map(permissionRes -> {
                            permissionRes.setChildren(getChildrens(permissionRes, permissionResList));
                            return permissionRes;
                        })
                .collect(Collectors.toList());

        return ResultVO.successList(folder);
    }

    @Override
    public ResultVO refreshToken(RefreshTokenDto dto) {
        String username = dto.getUsername();
        String refreshToken_ = dto.getRefreshToken();
        if (StringUtils.isBlank(username)) {
            return ResultVO.error(ResultCode.USERNAME_EMPTY_ERROR);
        }
        if (StringUtils.isBlank(refreshToken_)) {
            return ResultVO.error(ResultCode.REFRESH_TOKEN_EMPTY_ERROR);
        }
        Object redisRefreshToken = redisService.get(username);
        if (redisRefreshToken == null || redisRefreshToken == "") {
            return ResultVO.error(ResultCode.REFRESH_TOKEN_NOT_EXIST_ERROR);
        }
        // 请求的 refreshToken 与 缓存中的不一致
        if (!refreshToken_.equals(redisRefreshToken.toString())) {
            return ResultVO.error(ResultCode.REFRESH_TOKEN_NOT_EXIST_ERROR);
        }

        JwtUserDetails userDetails = (JwtUserDetails)selfUserDetailsService.loadUserByUsername(dto.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);

        Map result = new HashMap<>();
        result.put("token", token);

        return ResultVO.successData(result);
    }

    @Override
    public ResultVO logout() {
        String username = request.getAttribute("username").toString();
        redisService.remove(RedisConstant.PRE_REFRESH_TOKEN + username);
        return ResultVO.success();
    }

    @Override
    public ResultVO getUserInfo() {
        String username = request.getAttribute("username").toString();
        UpmsUser upmsUser = upmsUserExtMapper.selectByUsername(username);
        UpmsUserRes upmsUserRes = new UpmsUserRes();
        BeanUtils.copyProperties(upmsUser, upmsUserRes);

        List<UpmsRole> upmsRoleList = upmsPermissionExtMapper.selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
        List<String> roleIdList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(upmsRoleList)) {
            upmsRoleList.stream().forEach(item->{
                roleIdList.add(item.getRoleId());
                roleNameList.add(item.getTitle());
            });
        }
        String roleNameStr = Strings.join(roleNameList, ',');
        upmsUserRes.setRoleNameStr(roleNameStr);
        upmsUserRes.setRoleIdList(roleIdList);
        return ResultVO.successData(upmsUserRes);
    }

    @Override
    public ResultVO validToken(TokenDto dto) {
        String token = dto.getToken();
        boolean boo = true;
        try {
            boo = jwtTokenUtil.isTokenExpired(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultVO.successData(boo);
    }

    private List<PermissionRes> getChildrens(PermissionRes root, List<PermissionRes> all) {

        List<PermissionRes> childrens = all.stream()
                .filter(permissionRes -> permissionRes.getPid().equals(root.getPermissionId()))
                .map(permissionRes -> {
                    permissionRes.setChildren(getChildrens(permissionRes, all));
                    return permissionRes;
                })
                .collect(Collectors.toList());
        return childrens;
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        // 查询用户信息
        UpmsUser upmsUser = upmsUserExtMapper.selectByUsername(username);

        LOGGER.info("upmsUser=[{}] , username=[{}], password=[{}]", upmsUser, username, password);
        if (upmsUser == null) {
            throw new BadCredentialsException("Bad Credentials");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (!bCryptPasswordEncoder.matches(password, upmsUser.getPassword())) {
            // 没有通过认证则抛出密码错误异常
            throw new BadCredentialsException("密码错误");
        }
        if (upmsUser.getLocked()) {
            throw new LockedAccountException("当前账号已经被锁定");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }
}

class SimpleAuthenticationManager implements AuthenticationManager {

    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    // 构建一个角色列表
    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 验证方法
    @Override
    public Authentication authenticate(Authentication auth) throws org.springframework.security.core.AuthenticationException {
        return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), AUTHORITIES);
    }
}