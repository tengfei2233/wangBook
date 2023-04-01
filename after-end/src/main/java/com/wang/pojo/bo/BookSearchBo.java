package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-20:56
 * @description
 */

@Data
@ApiModel("书籍搜索业务对象")
public class BookSearchBo {

    @ApiModelProperty("查询方式，1：按书名，2：按作者名，3：按ISBN号")
    private Integer pattern;

    @ApiModelProperty("搜索关键词")
    private String key;

    @ApiModelProperty("分类id")
    private Long type;

}
