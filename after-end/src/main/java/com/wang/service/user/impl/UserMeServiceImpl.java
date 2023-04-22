package com.wang.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wang.exception.UserException;
import com.wang.mapper.UserMapper;
import com.wang.pojo.LoginUser;
import com.wang.pojo.User;
import com.wang.pojo.bo.UserUpdatePwdBo;
import com.wang.pojo.vo.UserVo;
import com.wang.service.user.UserMeService;
import com.wang.utils.RedisKey;
import com.wang.utils.RedisUtil;
import com.wang.utils.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 14:59
 * @Description
 **/

@Service
public class UserMeServiceImpl implements UserMeService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;

    @Override
    public UserVo info(HttpServletRequest request) {
        Long userId = SecurityUtil.getUserId();
        LoginUser loginUser = redisUtil.get(RedisKey.LOGIN_USER_KEY + userId);
        UserVo userVo = BeanUtil.copyProperties(loginUser.getUser(), UserVo.class);
        return userVo;
    }

    @Override
    public Boolean updatePwd(UserUpdatePwdBo bo, HttpServletRequest request) {
        // TODO 获取用户id
        Long userId = SecurityUtil.getUserId();
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getPassword)
                .eq(User::getUserId, userId)
                .eq(User::getStatus, 1));
        if (ObjectUtil.isNull(user)) {
            throw new UserException("用户不存在/用户被锁定");
        }
        if (!SecurityUtil.matchesPassword(bo.getOldPwd(), user.getPassword())) {
            throw new UserException("旧密码输入错误");
        }
        int update = userMapper.update(null, new LambdaUpdateWrapper<User>()
                .set(User::getPassword, SecurityUtil.encryptPassword(bo.getNewPwd()))
                .eq(User::getUserId, userId));
        return update >= 1;
    }
}
