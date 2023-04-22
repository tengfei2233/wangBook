package com.wang.controller.user;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import com.wang.pojo.bo.LoginBo;
import com.wang.service.user.UserLoginService;
import com.wang.utils.CaptchaUtil;
import com.wang.utils.R;
import com.wang.utils.RedisKey;
import com.wang.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-20-15:44
 * @description
 */

@RestController
@Api(tags = {"用户登录控制器"})
@RequestMapping("/user/login")
public class UserLoginController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserLoginService loginService;


    @ApiOperation("登录方法")
    @PostMapping("/login")
    public R<Map<String, String>> login(@RequestBody LoginBo bo) {
        return R.ok("登录成功",loginService.login(bo));
    }


    @ApiOperation("登出方法")
    @GetMapping("/logout")
    public String logout() {
        return "登出成功";
    }

    @ApiOperation("获取验证码")
    @GetMapping("/getCaptcha")
    public R<Object> getCaptcha() {
        String uuid = UUID.randomUUID().toString();
        // 四位数随机验证码
        CodeGenerator generator = CaptchaUtil.getCodeGenerator(4);
        AbstractCaptcha captchaDrawer = CaptchaUtil.getCaptchaDrawer();
        captchaDrawer.setGenerator(generator);
        captchaDrawer.createCode();
        String code = captchaDrawer.getCode();
        // 将验证码存储起来，存储两分钟
        redisUtil.set(RedisKey.CAPTCHA_KEY + uuid, code, 2);
        Map<String, Object> map = new HashMap<>();
        map.put("uuid", uuid);
        map.put("captcha", captchaDrawer.getImageBase64Data());
        return R.ok("请求成功", map);
    }

}
