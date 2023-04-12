package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 04 12 14:07
 * @Description
 **/


@Data
@ApiModel("添加订单业务类")
public class AddOrderBo {

    @ApiModelProperty("书籍id")
    private Long bookId;

    @ApiModelProperty("书籍数目")
    private Integer orderNum;

}
