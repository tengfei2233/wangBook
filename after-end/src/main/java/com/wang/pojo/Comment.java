package com.wang.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @description comment
 * @author 
 * @date 2023-03-31
 */
@Data
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
    * 评论id
    */
    @TableId
    private Long cmId;

    /**
    * 父评论id
    */
    private Long cmParentId;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 书籍id
    */
    private Long bookId;

    /**
    * 评论内容
    */
    private String cmContent;

    /**
    * 评论时间
    */
    private Date cmDate;

}