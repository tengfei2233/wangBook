package com.wang.service.user.impl;

import cn.hutool.core.util.ObjUtil;
import com.wang.exception.UserException;
import com.wang.pojo.LoginUser;
import com.wang.pojo.bo.LoginBo;
import com.wang.service.user.UserLoginService;
import com.wang.utils.AuthenticationContextHolder;
import com.wang.utils.JwtUtil;
import com.wang.utils.RedisKey;
import com.wang.utils.RedisUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 20:36
 * @Description
 **/

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtUtil jwtUtil;


    @Override
    public Map<String, String> login(LoginBo bo) {
        if (ObjUtil.isNull(bo)) {
            throw new UserException("登录参数为空");
        }
        String key = RedisKey.CAPTCHA_KEY + bo.getUuid();
        String captcha = redisUtil.get(key);
        redisUtil.del(key);
        if (ObjUtil.isNull(captcha) || !captcha.equals(bo.getCaptcha())) {
            throw new UserException("验证码失效/验证码错误");
        }
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(bo.getUsername(), bo.getPassword());
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
