package com.wang.service.user;

import com.wang.pojo.bo.LoginBo;
import com.wang.pojo.bo.PhoneLoginBo;
import com.wang.pojo.bo.UserRegisterBo;

import java.util.Map;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 20:36
 * @Description
 **/

public interface UserLoginService {
    Map<String, String> login(LoginBo bo);

    Map<String, String> phoneLogin(PhoneLoginBo bo);

    String getCode(String phone);

    Boolean register(UserRegisterBo bo);
}
