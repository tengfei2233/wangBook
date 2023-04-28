package com.wang.service.manage.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wang.exception.UserException;
import com.wang.mapper.UserMapper;
import com.wang.pojo.LoginUser;
import com.wang.pojo.User;
import com.wang.pojo.bo.UserUpdatePhoneBo;
import com.wang.pojo.bo.UserUpdatePwdBo;
import com.wang.pojo.vo.UserVo;
import com.wang.service.manage.ManageLoginService;
import com.wang.utils.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;

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

    @Override
    public UserVo info(HttpServletRequest request) {
        Long userId = SecurityUtil.getUserId();
        LoginUser loginUser = redisUtil.get(RedisKey.LOGIN_USER_KEY + userId);
        UserVo userVo = BeanUtil.copyProperties(loginUser.getUser(), UserVo.class);
        return userVo;
    }

    @Override
    public Boolean updatePwd(UserUpdatePwdBo bo) {
        if (ObjUtil.isNull(bo)) {
            throw new UserException("参数未空");
        }
        // 获取用户id
        Long userId = SecurityUtil.getUserId();
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getUserId, User::getPassword)
                .eq(User::getUserId, userId)
                .eq(User::getStatus, 1));
        if (ObjectUtil.isNull(user)) {
            throw new UserException("用户不存在/用户被锁定");
        }
        if (!SecurityUtil.matchesPassword(bo.getOldPwd(), user.getPassword())) {
            throw new UserException("旧密码输入错误");
        }
        String newPwd = SecurityUtil.encryptPassword(bo.getNewPwd());
        int update = userMapper.update(null, new LambdaUpdateWrapper<User>()
                .set(User::getPassword, newPwd)
                .eq(User::getUserId, userId));
        if (update >= 1) {
            LoginUser loginUser = redisUtil.get(RedisKey.LOGIN_USER_KEY + userId);
            loginUser.getUser().setPassword(newPwd);
            redisUtil.set(RedisKey.LOGIN_USER_KEY + userId, loginUser, 20);
        }
        return update >= 1;
    }

    @Override
    public Boolean updatePhone(UserUpdatePhoneBo bo) {
        if (ObjUtil.isNull(bo)) {
            throw new UserException("参数未空");
        }
        String code = redisUtil.get(RedisKey.PHONE_CODE + bo.getPhone());
        if (ObjUtil.isNull(code)) {
            throw new UserException("验证码失效");
        }
        if (!code.equals(bo.getCode())) {
            throw new UserException("验证码错误");
        }
        redisUtil.del(RedisKey.PHONE_CODE + bo.getPhone());
        // 获取用户id
        Long userId = SecurityUtil.getUserId();
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getUserId, User::getPhone)
                .eq(User::getUserId, userId)
                .eq(User::getStatus, 1));
        if (ObjectUtil.isNull(user)) {
            throw new UserException("用户不存在/用户被锁定");
        }
        // TODO 存在相同手机号情况，望周知
        if (ObjUtil.isNotNull(user.getPhone()) && user.getPhone().equals(bo.getPhone())) {
            throw new UserException("手机号相同");
        }

        int update = userMapper.update(null, new LambdaUpdateWrapper<User>()
                .set(User::getPhone, bo.getPhone())
                .eq(User::getUserId, userId));
        if (update >= 1) {
            LoginUser loginUser = redisUtil.get(RedisKey.LOGIN_USER_KEY + userId);
            loginUser.getUser().setPhone(bo.getPhone());
            redisUtil.set(RedisKey.LOGIN_USER_KEY + userId, loginUser, 20);
        }
        return update >= 1;
    }

    @Override
    public Boolean updateAvatar(String avatar) {
        // 获取用户id
        Long userId = SecurityUtil.getUserId();

        int update = userMapper.update(null, new LambdaUpdateWrapper<User>()
                .set(User::getAvatar, avatar)
                .eq(User::getUserId, userId));
        if (update >= 1) {
            LoginUser loginUser = redisUtil.get(RedisKey.LOGIN_USER_KEY + userId);
            loginUser.getUser().setAvatar(avatar);
            redisUtil.set(RedisKey.LOGIN_USER_KEY + userId, loginUser, 20);
        }
        return update >= 1;
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
}
