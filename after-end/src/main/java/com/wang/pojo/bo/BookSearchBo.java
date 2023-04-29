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

    @ApiModelProperty("查询方式，1：按书名，2：按作者名，3：按ISBN号(默认1)")
    private Integer pattern = 1;

    @ApiModelProperty("搜索关键词")
    private String key;

    @ApiModelProperty("分类id")
    private Long type;

    @ApiModelProperty("排序类型(1:默认,2:最新,3:热门)")
    private Integer sortType;

}
