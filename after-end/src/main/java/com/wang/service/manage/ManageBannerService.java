package com.wang.service.manage;

import com.wang.pojo.bo.BannerBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BannerVo;
import com.wang.pojo.vo.PageData;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 10:49
 * @Description
 **/

public interface ManageBannerService {
    PageData<BannerVo> getBannerList(PageQuery pageQuery);

    Boolean addBanner(BannerBo bo);

    Boolean updateBanner(BannerBo bo);

    Boolean deleteBanner(Long bnId);
}
