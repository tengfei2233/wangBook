package com.wang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: tengfei
 * @Create: 2023 04 12 15:28
 * @Description
 **/

@Data
@ApiModel("购物车视图对象")
public class CarVo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("订单价格")
    private BigDecimal orderPrice;

    @ApiModelProperty("书籍id")
    private Long bookId;

    @ApiModelProperty("订单数目")
    private Integer orderNum;

    @ApiModelProperty("书籍名称")
    private String bookName;

    @ApiModelProperty("书籍封面")
    private String bookCover;

    @ApiModelProperty("书籍作者")
    private String bookAuthor;

    @ApiModelProperty("ISBN号")
    private String bookIsbn;

}
