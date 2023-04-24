package com.wang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 12:21
 * @Description
 **/

@Data
@ApiModel("用户视图对象")
public class UserVo {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户名")
    private String username;


    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("用户类型")
    private Integer userType;

    @ApiModelProperty("注册时间")
    private Date addDate;

    @ApiModelProperty("用户状态,1:正常,0:禁用")
    private Integer status;
}
