package com.wang.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-20-15:44
 * @description
 */

@RestController
@Api(tags = {"用户登录控制器"})
@RequestMapping("/user")
public class UserLoginController {

    @ApiOperation("登录方法")
    @GetMapping("/login")
    public String login(){
        return "登录成功";
    }


    @ApiOperation(value = "登出方法")
    @GetMapping("/logout")
    public String logout(){
        return "登出成功";
    }

}
