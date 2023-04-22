package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-24-22:00
 * @description
 */

@Data
@ApiModel("用户登录业务对象")
public class LoginBo {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码id")
    private String uuid;

    @ApiModelProperty("验证码")
    private String captcha;

}
