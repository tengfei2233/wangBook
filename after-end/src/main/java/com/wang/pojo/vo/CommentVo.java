package com.wang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-22:39
 * @description
 */

@Data
@ApiModel("评论视图对象")
public class CommentVo {

    @ApiModelProperty("评论id")
    private Long cmId;

    @ApiModelProperty("父评论id")
    private Long cmParentId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("书籍id")
    private Long bookId;

    @ApiModelProperty("评论内容")
    private String cmContent;

    @ApiModelProperty("评论时间")
    private Date cmDate;

    @ApiModelProperty("子评论")
    private List<CommentVo> childComment;

}
