package com.wang.service.user;

import com.wang.pojo.bo.UserUpdatePhoneBo;
import com.wang.pojo.bo.UserUpdatePwdBo;
import com.wang.pojo.vo.UserVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 14:59
 * @Description
 **/

public interface UserMeService {
    UserVo info(HttpServletRequest request);

    Boolean updatePwd(UserUpdatePwdBo bo, HttpServletRequest request);

    Boolean updatePhone(UserUpdatePhoneBo bo);

    Boolean updateAvatar(String avatar);

    String getCode(String phone);
}
