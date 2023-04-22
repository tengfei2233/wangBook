package com.wang.exception;

import com.alibaba.fastjson2.JSON;
import com.wang.utils.R;
import com.wang.utils.ServletUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-20-21:17
 * @description SpringSecurity未登录访问异常
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ServletUtil.renderString(response, JSON.toJSONString(R.fail("访问异常，请登录", 401)));
    }
}
