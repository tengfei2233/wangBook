package com.wang.controller.manage;

import com.wang.pojo.bo.UserUpdatePhoneBo;
import com.wang.pojo.bo.UserUpdatePwdBo;
import com.wang.pojo.vo.UserVo;
import com.wang.service.manage.ManageLoginService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @ApiOperation("个人信息")
    @GetMapping("/info")
    public R<UserVo> info(HttpServletRequest request) {
        return R.ok("查询成功", loginService.info(request));
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePwd")
    public R<Void> updatePwd(@RequestBody UserUpdatePwdBo bo) {
        return loginService.updatePwd(bo) ? R.ok("修改成功") : R.fail("修改失败");
    }

    @ApiOperation("修改手机号")
    @PostMapping("/updatePhone")
    public R<Void> updatePhone(@RequestBody UserUpdatePhoneBo bo) {
        return loginService.updatePhone(bo) ? R.ok("修改成功") : R.fail("修改失败");
    }

    @ApiOperation("修改头像")
    @PostMapping("/updateAvatar")
    public R<Void> updateAvatar(@RequestParam("avatar") @ApiParam("头像") String avatar) {
        return loginService.updateAvatar(avatar) ? R.ok("修改成功") : R.fail("修改失败");
    }

    @ApiOperation("获取手机验证码")
    @PostMapping("/getCode")
    public R<String> getCode(@RequestParam("phone") @ApiParam("手机号") String phone) {
        return R.ok("获取成功", loginService.getCode(phone));
    }

}
