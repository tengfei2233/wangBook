package com.wang.service.manage.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.exception.OtherException;
import com.wang.mapper.BannerMapper;
import com.wang.pojo.Banner;
import com.wang.pojo.bo.BannerBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BannerVo;
import com.wang.pojo.vo.PageData;
import com.wang.service.manage.ManageBannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 10:50
 * @Description
 **/

@Service
public class ManageBannerServiceImpl implements ManageBannerService {

    @Resource
    private BannerMapper bannerMapper;

    @Override
    public PageData<BannerVo> getBannerList(PageQuery pageQuery) {
        Page<Banner> bannerPage = bannerMapper.selectPage(pageQuery.build(), new LambdaQueryWrapper<Banner>()
                .eq(Banner::getStatus, 1)
                .orderByDesc(Banner::getBnId));
        List<BannerVo> bannerVos = BeanUtil.copyToList(bannerPage.getRecords(), BannerVo.class);
        return PageData.build(bannerPage.getTotal(), bannerVos);
    }

    @Override
    public Boolean addBanner(BannerBo bo) {
        if (ObjUtil.isNull(bo)) {
            throw new OtherException("参数为空");
        }

        Banner banner = BeanUtil.copyProperties(bo, Banner.class);
        banner.setBnId(null);
        banner.setBnDate(new Date());
        int insert = bannerMapper.insert(banner);
        return insert >= 0;
    }

    @Override
    public Boolean updateBanner(BannerBo bo) {
        if (ObjUtil.isNull(bo) || ObjUtil.isNull(bo.getBnId())) {
            throw new OtherException("参数为空");
        }
        Banner banner = BeanUtil.copyProperties(bo, Banner.class);
        int update = bannerMapper.updateById(banner);
        return update >= 1;
    }

    @Override
    public Boolean deleteBanner(Long bnId) {
        int update = bannerMapper.update(null, new LambdaUpdateWrapper<Banner>()
                .eq(Banner::getBnId, bnId)
                .set(Banner::getStatus, 0));
        return update >= 1;
    }
}
