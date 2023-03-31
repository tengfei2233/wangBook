package com.wang.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-31-22:30
 * @description
 */

@Data
public class BaseEntity implements Serializable {

    /**
     * 状态：
     * 0：禁用，1：正常
     * 0：下架，1：上架
     * 0：删除，1：正常
     * 。。。。
     */
    private Integer status;

}
