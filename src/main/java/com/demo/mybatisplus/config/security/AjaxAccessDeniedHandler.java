package com.demo.mybatisplus.config.security;

import cn.hutool.json.JSON;
import com.demo.mybatisplus.enums.ResultCode;
import com.demo.mybatisplus.model.vo.ResultVO;
import com.google.gson.Gson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验请求携带的token如果不正确，将返回该内容
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultVO resultVO = new ResultVO(ResultCode.TOKEN_ERROR);
        httpServletResponse.getWriter().write(new Gson().toJson(resultVO));
    }
}
