package com.wang.service.manage.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.mapper.UserMapper;
import com.wang.pojo.User;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.bo.UserSearchBo;
import com.wang.pojo.vo.PageData;
import com.wang.pojo.vo.UserVo;
import com.wang.service.manage.ManageUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 17:44
 * @Description
 **/

@Service
public class ManageUserServiceImpl implements ManageUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public PageData<UserVo> getUserList(UserSearchBo searchBo, PageQuery pageQuery) {
        Page<User> userPage = userMapper.selectPage(pageQuery.build(), new LambdaQueryWrapper<User>()
                .eq(ObjectUtil.isNotNull(searchBo.getStatus()), User::getStatus, searchBo.getStatus())
                .and(ObjectUtil.isNotNull(searchBo.getSearchKey()), wrapper -> {
                    wrapper.eq(User::getPhone, searchBo.getSearchKey())
                            .or()
                            .like(User::getUsername, searchBo.getSearchKey());
                }));
        List<UserVo> userVos = BeanUtil.copyToList(userPage.getRecords(), UserVo.class);
        return PageData.build(userPage.getTotal(), userVos);
    }

    @Override
    public Boolean lockUser(Long userId) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getStatus)
                .eq(User::getUserId, userId));
        int update = userMapper.update(null, new LambdaUpdateWrapper<User>()
                .eq(User::getUserId, userId)
                .set(user.getStatus() == 1, User::getStatus, 0)
                .set(user.getStatus() == 0, User::getStatus, 1));
        return update >= 1;
    }
}
