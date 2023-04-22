package com.wang.service.manage;

import java.util.Map;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 21:31
 * @Description
 **/

public interface ManageLoginService {
    Map<String,String> login(String username, String password);
}
