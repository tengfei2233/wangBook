package com.wang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-21:14
 * @description
 */

@Data
@ApiModel("书籍视图对象")
public class BookVo {

    @ApiModelProperty("书籍id")
    private Long bookId;

    @ApiModelProperty("书名")
    private String bookName;

    @ApiModelProperty("作者名")
    private String bookAuthor;

    @ApiModelProperty("书籍描述")
    private String bookDesc;

    @ApiModelProperty("书籍封面")
    private String bookCover;

    @ApiModelProperty("书籍ISBN号")
    private String bookIsbn;

    @ApiModelProperty("库存量")
    private Integer bookStock;

    @ApiModelProperty("单价")
    private BigDecimal bookPrice;

    @ApiModelProperty("上架时间")
    private BigDecimal bookAddDate;

}
