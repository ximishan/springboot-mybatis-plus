package com.demo.mybatisplus.filter;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.demo.mybatisplus.config.security.SecurityConfig;
import com.demo.mybatisplus.enums.ResultCode;
import com.demo.mybatisplus.model.vo.ResultVO;
import com.demo.mybatisplus.utils.jwt.JwtTokenUtil;
import com.demo.mybatisplus.utils.jwt.JwtUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Service
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.Header}")
    private String jwtHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 判断是否是未拦截的地址
        if (isFilterExcludeRequest(request)) {
            chain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader(jwtHeader);
            if (authHeader != null && authHeader.startsWith(tokenHead)) {
                final String authToken = authHeader.substring(tokenHead.length());
//                LOGGER.info("token=[{}]", authToken);
                String username = jwtTokenUtil.getUsernameFromToken(authToken);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    JwtUserDetails userDetails = jwtTokenUtil.getUserFromToken(authToken);

                    if (userDetails != null) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        request.setAttribute("username", username);
                        request.setAttribute("userId", userDetails.getUserId());

                        LOGGER.info("username=[{}]", username);
                        LOGGER.info("userId=[{}]", userDetails.getUserId());

                        chain.doFilter(request, response);
                    }
                } else {
                    // token 有问题直接返回错误信息给前端
                    returnJson(response);
                }
            } else {
                // token 有问题直接返回错误信息给前端
                returnJson(response);
            }
        }
    }

    /**
     * 判断是否是 过滤器直接放行的请求
     * <br/>主要用于静态资源的放行
     * @param
     * @return
     */
    private boolean isFilterExcludeRequest(HttpServletRequest request) {
        for (String reg : SecurityConfig.excludeMapping()) {
            if (new AntPathMatcher().match(reg, request.getServletPath())) {
                return true;
            }
        }
        return false;
    }

    public void returnJson(HttpServletResponse response){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            ResultVO resultVO = new ResultVO(ResultCode.TOKEN_ERROR);
            response.getWriter().write(JSONUtil.toJsonStr(resultVO));
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
