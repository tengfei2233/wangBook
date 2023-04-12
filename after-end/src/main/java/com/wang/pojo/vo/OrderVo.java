package com.wang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: tengfei
 * @Create: 2023 04 12 14:37
 * @Description
 **/


@Data
@ApiModel("订单列表视图对象")
public class OrderVo {

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("订单价格")
    private BigDecimal orderPrice;

    @ApiModelProperty("书籍id")
    private Long bookId;

    @ApiModelProperty("下单时间")
    private Date orderDate;

    @ApiModelProperty("付款时间")
    private Date buyDate;

    @ApiModelProperty("订单数目")
    private Integer orderNum;

    @ApiModelProperty("书籍名称")
    private String bookName;

    @ApiModelProperty("书籍封面")
    private String bookCover;

    @ApiModelProperty("订单状态,1:已付款,0:未付款")
    private Integer status;

}
