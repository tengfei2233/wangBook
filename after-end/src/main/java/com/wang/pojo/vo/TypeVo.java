package com.wang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-20:49
 * @description
 */

@Data
@ApiModel("书籍类别视图对象")
public class TypeVo {

    @ApiModelProperty("类别id")
    private Long typeId;

    @ApiModelProperty("类别名称")
    private String typeName;
}
