package com.wang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 07 06 17:58
 * @Description
 **/

@Data
@ApiModel("二维码视图对象")
public class QrcodeVo {

    @ApiModelProperty("二维码url")
    private String qrcode;

    @ApiModelProperty("订单id")
    private String orderId;

}
