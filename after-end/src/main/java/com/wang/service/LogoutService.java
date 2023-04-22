package com.wang.service;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson2.JSON;
import com.wang.pojo.LoginUser;
import com.wang.utils.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 21:46
 * @Description
 **/

@Service
public class LogoutService implements LogoutSuccessHandler {

    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = jwtUtil.getLoginUser(request);
        if (ObjUtil.isNotNull(loginUser)) {
            redisUtil.del(RedisKey.LOGIN_USER_KEY + loginUser.getUserId());
        }
        ServletUtil.renderString(response, JSON.toJSONString(R.ok("推出登录成功")));
    }
}
