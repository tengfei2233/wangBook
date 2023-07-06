package com.wang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: tengfei
 * @Create: 2023 07 06 17:26
 * @Description
 **/

@Data
@ApiModel("订单状态试图对象")
public class OrderInfoVo {

    @ApiModelProperty("订单id")
    private String orderId;
    @ApiModelProperty("支付宝交易id")
    private String alipayId;
    @ApiModelProperty("购买时间")
    private Date buyDate;
    @ApiModelProperty("/*\n" +
            "        0：交易不存在\n" +
            "        1：创建交易，等待付款\n" +
            "        2：交易超时\n" +
            "        3：交易成功\n" +
            "        4：交易成功，不可退款\n" +
            "         */")
    private Integer orderStatus;

}
