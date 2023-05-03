package com.wang.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: tengfei
 * @Create: 2023 04 12 15:43
 * @Description
 **/

@Data
public class Car {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 书籍id
     */
    private Long bookId;

    /**
     * 用户id
     */
    private Long userId;


    /**
     * 购买数量
     */
    private Integer orderNum;

}
