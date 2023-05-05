package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 05 05 13:01
 * @Description
 **/

@Data
@ApiModel("用户注册业务实体")
public class UserRegisterBo {

    @ApiModelProperty("用户名")
    private String username;


    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

}
