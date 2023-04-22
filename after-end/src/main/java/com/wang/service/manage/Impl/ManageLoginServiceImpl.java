package com.wang.service.manage.Impl;

import cn.hutool.core.util.ObjUtil;
import com.wang.exception.UserException;
import com.wang.pojo.LoginUser;
import com.wang.service.manage.ManageLoginService;
import com.wang.utils.AuthenticationContextHolder;
import com.wang.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 21:31
 * @Description
 **/

@Service
public class ManageLoginServiceImpl implements ManageLoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public Map<String, String> login(String username, String password) {
        if (ObjUtil.isNull(username) || ObjUtil.isNull(password)) {
            throw new UserException("登录参数为空");
        }
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        } finally {
            AuthenticationContextHolder.clearContext();
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = jwtUtil.createJwt(loginUser);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
