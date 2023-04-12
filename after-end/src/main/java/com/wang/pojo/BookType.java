package com.wang.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description book_type
 * @author 
 * @date 2023-03-31
 */
@Data
public class BookType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long id;

    /**
    * 书籍id
    */
    private Long bookId;

    /**
    * 书籍类型id
    */
    private Long typeId;

}