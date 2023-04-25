package com.wang.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-31-22:34
 * @description
 */

@Data
@ApiModel("轮播图视图对象")
public class BannerVo {

    @ApiModelProperty("轮播图id")
    private Long bnId;

    @ApiModelProperty("轮播图地址")
    private String bnUrl;

    @ApiModelProperty("轮播图内容")
    private String bnContent;

    @ApiModelProperty("轮播图是否展示(0:不展示,1:展示)")
    private Integer bnIsShow;

    @ApiModelProperty("添加时间")
    private Date bnDate;

}
