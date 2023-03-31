package com.wang.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wang.mapper.BannerMapper;
import com.wang.pojo.Banner;
import com.wang.pojo.vo.BannerVo;
import com.wang.service.user.UserIndexService;
import com.wang.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-31-22:46
 * @description
 */

@Service
public class UserIndexServiceImpl implements UserIndexService {

    @Resource
    private BannerMapper indexMapper;

    @Override
    public BannerVo getBannerList(Integer count) {
        List<Banner> banners = indexMapper.selectList(new LambdaQueryWrapper<Banner>()
                .eq(Banner::getBnIsShow, 1)
                .eq(Banner::getStatus, 1)
                .orderByDesc(Banner::getBnDate)
                .last(" limit " + count));
        BannerVo bannerVo = BeanUtil.copyProperties(banners, BannerVo.class);
        return bannerVo;
    }
}
