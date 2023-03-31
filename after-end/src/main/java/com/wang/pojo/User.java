package com.wang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @description user
 * @author 
 * @date 2023-03-31
 */
@Data
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 电话号码
    */
    private String phone;

    /**
    * 头像
    */
    private String avatar;

    /**
    * 0：超管，1：普管，2：普通用户
    */
    private Integer userType;
}