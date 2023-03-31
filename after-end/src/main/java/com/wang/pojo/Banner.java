package com.wang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author
 * @description banner
 * @date 2023-03-31
 */
@Data
public class Banner extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 轮播图id
     */
    private Long bnId;

    /**
     * 轮播图url
     */
    private String bnUrl;

    /**
     * 轮播图内容
     */
    private String bnContent;

    /**
     * 0：不展示，1：展示
     */
    private Integer bnIsShow;

    /**
     * 上传时间
     */
    private Date bnDate;

}