package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-24-22:02
 * @description
 */

@Data
@ApiModel("手机号登录业务对象")
public class PhoneLoginBo {

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("验证码")
    private String phoneCode;

}
