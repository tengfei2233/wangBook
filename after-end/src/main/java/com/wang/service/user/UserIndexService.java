package com.wang.service.user;

import com.wang.pojo.vo.BannerVo;
import com.wang.pojo.vo.BookVo;

import java.util.List;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-31-22:45
 * @description
 */
public interface UserIndexService {

    List<BannerVo> getBannerList(Integer count);

    List<BookVo> getNewBooksList(Integer count);

    List<BookVo> getHotBooksList(Integer count);
}
