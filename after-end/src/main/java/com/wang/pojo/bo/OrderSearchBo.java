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

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("订单状态,1:已付款,0:未付款")
    private Integer status;

}
