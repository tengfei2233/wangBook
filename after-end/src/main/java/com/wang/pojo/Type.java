package com.wang.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description type
 * @author 
 * @date 2023-03-31
 */
@Data
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 书籍类型id
    */
    private Long typeId;

    /**
    * 类型名称
    */
    private String typeName;

}