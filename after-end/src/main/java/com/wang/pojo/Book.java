package com.wang.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @description book
 * @date 2023-03-31
 */
@Data
public class Book extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 书籍id
     */
    @TableId
    private Long bookId;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 作者名
     */
    private String bookAuthor;

    /**
     * 书籍描述
     */
    private String bookDesc;

    /**
     * 书籍封面
     */
    private String bookCover;

    /**
     * isbn号
     */
    private String bookIsbn;

    /**
     * 库存
     */
    private Integer bookStock;

    /**
     * 价格
     */
    private BigDecimal bookPrice;

    /**
     * 上架时间
     */
    private Date bookAddDate;
}