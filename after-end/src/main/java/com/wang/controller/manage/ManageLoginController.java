package com.wang.controller.manage;

import com.wang.service.manage.ManageLoginService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-20-15:47
 * @description
 */

@Api(tags = {"管理员登录控制器"})
@RestController
@RequestMapping("/manage/login")
public class ManageLoginController {

    @Resource
    private ManageLoginService loginService;

    @ApiOperation("登录方法")
    @PostMapping("/login")
    public R<Map<String, String>> login(@RequestParam("username") @ApiParam("用户名/手机号") String username,
                                     @RequestParam("password") @ApiParam("密码") String password) {
        return R.ok("登录成功", loginService.login(username, password));
    }

    @ApiOperation("登出方法")
    @GetMapping("/logout")
    public String logout() {
        return "登出成功";
    }

}
