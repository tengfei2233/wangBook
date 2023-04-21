package com.wang.controller.user;

import com.wang.pojo.bo.UserUpdatePwdBo;
import com.wang.pojo.vo.UserVo;
import com.wang.service.user.UserMeService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 11:08
 * @Description 用户个人信息控制器
 **/

@RestController
@Api(tags = {"用户信息控制器"})
@RequestMapping("/user/me")
public class UserMeController {

    @Resource
    private UserMeService meService;

    @ApiOperation("个人信息")
    @GetMapping("/info")
    public R<UserVo> info(HttpServletRequest request) {
        return R.ok("查询成功", meService.info(request));
    }

    @ApiOperation("修改密码")
    @GetMapping("/updatePwd")
    public R<Void> updatePwd(@RequestBody UserUpdatePwdBo bo, HttpServletRequest request) {
        return meService.updatePwd(bo, request) ? R.ok("修改成功") : R.fail("修改失败");
    }

}
