package com.wang.service;

import cn.hutool.core.util.ObjUtil;
import com.wang.pojo.LoginUser;
import com.wang.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 16:55
 * @Description Token认证过滤器
 **/

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LoginUser loginUser = jwtUtil.getLoginUser(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjUtil.isNotNull(loginUser) && ObjUtil.isNull(authentication)) {
            // 代表登录未授权
            jwtUtil.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
