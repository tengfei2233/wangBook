package com.wang.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 10:59
 * @Description
 **/

@Data
@ApiModel("轮播图业务对象")
public class BannerBo {

    @ApiModelProperty("轮播图id")
    private Long bnId;

    @ApiModelProperty("轮播图url")
    private String bnUrl;

    @ApiModelProperty("轮播图内容")
    private String bnContent;

    @ApiModelProperty("轮播图是否展示(0:不展示,1:展示)")
    private Integer bnIsShow;


}
