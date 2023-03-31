package com.wang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description order
 * @author 
 * @date 2023-03-31
 */
@Data
public class Order extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * 订单id
    */
    private Long orderId;

    /**
    * 订单价格
    */
    private BigDecimal orderPrice;

    /**
    * 书籍id
    */
    private Long bookId;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 下单时间
    */
    private Date orderDate;

    /**
    * 购买时间
    */
    private Date buyDate;

    /**
    * 购买数量
    */
    private Integer orderNum;

    /**
    * 支付宝交易编号
    */
    private String alipayNo;
}