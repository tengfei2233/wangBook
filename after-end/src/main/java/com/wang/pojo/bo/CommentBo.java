package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 04 12 11:49
 * @Description
 **/

@Data
@ApiModel("评论添加业务对象")
public class  CommentBo {

    @ApiModelProperty("书籍id")
    private Long bookId;

    @ApiModelProperty("评论内容")
    private String cmContent;

    @ApiModelProperty("父评论id")
    private Long cmParentId;

}
