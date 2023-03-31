package com.wang.service.user;

import com.wang.pojo.vo.BannerVo;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-31-22:45
 * @description
 */
public interface UserIndexService {

    BannerVo getBannerList(Integer count);

}
