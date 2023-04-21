package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 17:46
 * @Description
 **/

@ApiModel("用户搜索业务对象")
@Data
public class UserSearchBo {

    @ApiModelProperty("搜索内容")
    private String searchKey;

    @ApiModelProperty("用户状态(0:禁用,1:正常)")
    private Integer status;


}
