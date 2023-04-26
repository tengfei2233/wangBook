package com.wang.service.manage;

import com.wang.pojo.bo.UserUpdatePhoneBo;
import com.wang.pojo.bo.UserUpdatePwdBo;
import com.wang.pojo.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 21:31
 * @Description
 **/

public interface ManageLoginService {
    Map<String,String> login(String username, String password);

    UserVo info(HttpServletRequest request);

    Boolean updatePwd(UserUpdatePwdBo bo);

    Boolean updatePhone(UserUpdatePhoneBo bo);

    Boolean updateAvatar(String avatar);

    String getCode(String phone);
}
