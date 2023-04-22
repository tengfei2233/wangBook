package com.wang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 11:24
 * @Description
 **/

@Data
@ApiModel("管理员订单视图对象")
public class ManOrderVo extends OrderVo {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("手机号")
    private String phone;

}
