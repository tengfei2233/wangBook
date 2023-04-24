package com.wang.service.manage;

import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.bo.UserBo;
import com.wang.pojo.bo.UserSearchBo;
import com.wang.pojo.vo.PageData;
import com.wang.pojo.vo.UserVo;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 17:44
 * @Description
 **/

public interface ManageUserService {
    PageData<UserVo> getUserList(UserSearchBo searchBo, PageQuery pageQuery);

    Boolean lockUser(Long userId);

    Boolean addUser(UserBo bo);
}
