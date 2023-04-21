package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 0:02
 * @Description
 **/

@Data
@ApiModel("书籍业务对象")
public class BookBo {

    @ApiModelProperty("书籍id")
    private Long bookId;

    @ApiModelProperty("书籍名称")
    private String bookName;

    @ApiModelProperty("书籍作者")
    private String bookAuthor;

    @ApiModelProperty("书籍描述")
    private String bookDesc;

    @ApiModelProperty("书籍封面")
    private String bookCover;

    @ApiModelProperty("ISBN号")
    private String bookIsbn;

    @ApiModelProperty("库存")
    private Integer bookStock;

    @ApiModelProperty("价格")
    private BigDecimal bookPrice;


}
