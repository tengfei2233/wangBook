package com.wang.service;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.exception.UserException;
import com.wang.mapper.UserMapper;
import com.wang.pojo.LoginUser;
import com.wang.pojo.User;
import com.wang.utils.AuthenticationContextHolder;
import com.wang.utils.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 20:52
 * @Description
 **/

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        if (isPhone(username)) {
            user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, username));
        } else {
            user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .eq(User::getUsername, username));
        }
        if (ObjUtil.isNull(user)) {
            throw new UserException("账号不存在");
        } else if (user.getStatus() == 0) {
            throw new UserException("账号被锁定");
        }
        // 比较密码
        Authentication authentication = AuthenticationContextHolder.getContext();
        String password = authentication.getCredentials().toString();
        boolean PwdPass = SecurityUtil.matchesPassword(password, user.getPassword());
        if (!PwdPass) {
            throw new UserException("密码错误");
        }
        user.setPassword(null);
        return new LoginUser(user);
    }

    private boolean isPhone(String username) {
        boolean matches = Pattern.matches("^1[3-9]\\d{9}$", username);
        return matches;
    }

}
