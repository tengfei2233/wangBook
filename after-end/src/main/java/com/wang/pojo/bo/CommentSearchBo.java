package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 05 04 17:08
 * @Description
 **/

@Data
@ApiModel("评论搜索业务对象")
public class CommentSearchBo {

    @ApiModelProperty("查询方式，1：书籍名称，2：ISBN")
    private Integer pattern = 1;

    @ApiModelProperty("搜索关键词")
    private String val;


}
