package com.wang.controller.user;

import com.wang.pojo.bo.UserUpdatePhoneBo;
import com.wang.pojo.bo.UserUpdatePwdBo;
import com.wang.pojo.vo.UserVo;
import com.wang.service.user.UserMeService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 11:08
 * @Description 用户个人信息控制器
 **/

@RestController
@Api(tags = {"用户信息控制器"})
@RequestMapping("/user/user")
public class UserMeController {

    @Resource
    private UserMeService meService;

    @ApiOperation("个人信息")
    @GetMapping("/info")
    public R<UserVo> info(HttpServletRequest request) {
        return R.ok("查询成功", meService.info(request));
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePwd")
    public R<Void> updatePwd(@RequestBody UserUpdatePwdBo bo, HttpServletRequest request) {
        return meService.updatePwd(bo, request) ? R.ok("修改成功") : R.fail("修改失败");
    }

    @ApiOperation("修改手机号")
    @PostMapping("/updatePhone")
    public R<Void> updatePhone(@RequestBody UserUpdatePhoneBo bo) {
        return meService.updatePhone(bo) ? R.ok("修改成功") : R.fail("修改失败");
    }

    @ApiOperation("修改头像")
    @PostMapping("/updateAvatar")
    public R<Void> updateAvatar(@RequestParam("avatar") @ApiParam("头像") String avatar) {
        return meService.updateAvatar(avatar) ? R.ok("修改成功") : R.fail("修改失败");
    }

    @ApiOperation("获取手机验证码")
    @PostMapping("/getCode")
    public R<String> getCode(@RequestParam("phone") @ApiParam("手机号") String phone) {
        return R.ok("获取成功", meService.getCode(phone));
    }

}
