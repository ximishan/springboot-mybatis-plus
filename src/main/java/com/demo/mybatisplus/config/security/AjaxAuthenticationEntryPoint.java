package com.demo.mybatisplus.config.security;

import com.demo.mybatisplus.enums.ResultCode;
import com.demo.mybatisplus.model.vo.ResultVO;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登陆直接返回该对象
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultVO resultVO = new ResultVO(ResultCode.UNAUTHORIZED_ERROR);
        httpServletResponse.getWriter().write(new Gson().toJson(resultVO));
    }
}
