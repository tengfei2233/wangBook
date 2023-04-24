package com.wang.controller.manage;

import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.bo.UserSearchBo;
import com.wang.pojo.vo.PageData;
import com.wang.pojo.vo.UserVo;
import com.wang.service.manage.ManageUserService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 17:42
 * @Description
 **/

@Api(tags = {"用户管理控制器"})
@RestController
@RequestMapping("/manage/user")
public class ManageUserController {

    @Resource
    private ManageUserService userService;

    @ApiOperation("用户列表")
    @GetMapping("/users")
    public R<PageData<UserVo>> getUserList(UserSearchBo searchBo, PageQuery pageQuery) {
        return R.ok("请求成功", userService.getUserList(searchBo, pageQuery));
    }

    @ApiOperation("封禁/解封用户")
    @PostMapping("/lock")
    public R<Void> lockUser(@RequestParam("userId")@Param("用户id") Long userId ) {
        return userService.lockUser(userId)?R.ok("更改成功"):R.fail("更改失败");
    }


}
