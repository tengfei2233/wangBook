package com.wang.exception;

import com.alibaba.fastjson2.JSON;
import com.wang.utils.R;
import com.wang.utils.ServletUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 16:30
 * @Description
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ServletUtil.renderString(response, JSON.toJSONString(R.fail("访问异常，无访问权限", 403)));
    }
}
