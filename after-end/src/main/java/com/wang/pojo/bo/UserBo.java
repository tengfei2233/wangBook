package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 04 24 15:47
 * @Description
 **/

@Data
@ApiModel("用户业务实体")
public class UserBo {


    @ApiModelProperty("用户名")
    private String username;


    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("头像")
    private String avatar;


}
