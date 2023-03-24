package com.wang.pojo.bo;

import lombok.Data;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-24-22:00
 * @description
 */

@Data
public class LoginBo {

    private String userName;

    private String passWord;

    private String uuid;

    private String captcha;

}
