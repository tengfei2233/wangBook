package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 17:20
 * @Description
 **/

@Data
@ApiModel("用户修改手机号业务对象")
public class UserUpdatePhoneBo {

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("验证码")
    private String code;

}
