package com.wang.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.exception.UserException;
import com.wang.mapper.UserMapper;
import com.wang.pojo.LoginUser;
import com.wang.pojo.User;
import com.wang.pojo.bo.LoginBo;
import com.wang.pojo.bo.PhoneLoginBo;
import com.wang.pojo.bo.UserRegisterBo;
import com.wang.service.user.UserLoginService;
import com.wang.utils.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private UserMapper userMapper;


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
        return loginDetail(bo.getUsername(), bo.getPassword(), false);
    }

    @Override
    public Map<String, String> phoneLogin(PhoneLoginBo bo) {
        if (ObjUtil.isNull(bo)) {
            throw new UserException("登录参数为空");
        }
        String phoneCode = redisUtil.get(RedisKey.PHONE_CODE + bo.getPhone());
        if (ObjUtil.isNull(phoneCode)) {
            throw new UserException("验证码已过期");
        }
        if (!phoneCode.equals(bo.getPhoneCode())) {
            throw new UserException("验证码错误");
        }
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getPassword)
                .eq(User::getPhone, bo.getPhone()));
        if (ObjUtil.isNull(user)) {
            throw new UserException("用户不存在|账号被封禁");
        }
        Map<String, String> map = loginDetail(bo.getPhone(), user.getPassword(), true);
        redisUtil.del(RedisKey.PHONE_CODE + bo.getPhone());
        return map;
    }

    @Override
    public String getCode(String phone) {
        if (ObjUtil.isNull(phone)) {
            throw new UserException("手机号为空");
        }
        String code = redisUtil.get(RedisKey.PHONE_CODE + phone);
        if (ObjUtil.isNotNull(code)) {
            return code;
        }
        String i = new Random().nextInt(9999) % 9000 + 1000 + "";
        redisUtil.set(RedisKey.PHONE_CODE + phone, i, 10);
        // TODO 接入短信发送平台
        return i;
    }

    @Override
    public Boolean register(UserRegisterBo bo) {
        if (ObjUtil.isNull(bo)) {
            throw new UserException("参数为kong");
        }
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getUserId)
                .eq(User::getUsername, bo.getUsername()));
        if (ObjUtil.isNotNull(user)) {
            throw new UserException("用户名已存在");
        }
        user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getUserId)
                .eq(User::getPhone, bo.getPhone()));
        if (ObjUtil.isNotNull(user)) {
            throw new UserException("手机号已被注册，请登录");
        }
        user = BeanUtil.copyProperties(bo, User.class);
        user.setPassword(SecurityUtil.encryptPassword(bo.getPassword()));
        user.setUserType(0);
        user.setAddDate(new Date());
        user.setStatus(1);
        int insert = userMapper.insert(user);
        redisUtil.del(RedisKey.PHONE_CODE + bo.getPhone());
        return insert >= 1;
    }

    private Map<String, String> loginDetail(String username, String password, boolean isPhone) {
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = null;
            if (isPhone) {
                List<SimpleGrantedAuthority> list = Arrays.asList(new SimpleGrantedAuthority("0"));
                authenticationToken = new UsernamePasswordAuthenticationToken(username, SecurityUtil.decodePassword(password), list);
            } else {
                authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            }

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
