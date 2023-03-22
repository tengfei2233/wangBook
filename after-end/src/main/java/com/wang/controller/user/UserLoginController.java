package com.wang.controller.user;

import com.wang.utils.R;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @Resource
    private CaptchaService captchaService;

    @ApiOperation("登录方法")
    @GetMapping("/login")
    public String login() {
        return "登录成功";
    }


    @ApiOperation("登出方法")
    @GetMapping("/logout")
    public String logout() {
        return "登出成功";
    }

    @ApiOperation("获取验证码")
    @PostMapping("/getCaptcha")
    public R<Object> getCaptcha(@RequestBody CaptchaVO captchaVO, HttpServletRequest request) {
        captchaVO.setBrowserInfo(getRemoteId(request));
        return R.ok("请求成功", captchaService.get(captchaVO).getRepData());
    }

    private static String getRemoteId(HttpServletRequest request) {
        String xfwd = request.getHeader("X-Forwarded-For");
        String ip = getRemoteIpFromXfwd(xfwd);
        String ua = request.getHeader("user-agent");
        if (StringUtils.isNotBlank(ip)) {
            return ip + ua;
        }
        return request.getRemoteAddr() + ua;
    }

    private static String getRemoteIpFromXfwd(String xfwd) {
        if (StringUtils.isNotBlank(xfwd)) {
            String[] ipList = xfwd.split(",");
            return StringUtils.trim(ipList[0]);
        }
        return null;
    }

}
