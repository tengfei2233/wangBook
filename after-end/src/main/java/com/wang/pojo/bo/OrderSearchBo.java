package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 11:37
 * @Description
 **/

@Data
@ApiModel("订单搜索业务对象")
public class OrderSearchBo {

    @ApiModelProperty("模式(1:订单id,2:用户名/手机号,3:书籍名称)")
    private Integer pattern;

    @ApiModelProperty("搜索值")
    private String val;

    @ApiModelProperty("订单状态,1:已付款,0:未付款")
    private Integer status;


}
