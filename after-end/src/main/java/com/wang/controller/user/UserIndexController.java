package com.wang.controller.user;

import com.wang.pojo.vo.BannerVo;
import com.wang.service.user.UserIndexService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-31-22:12
 * @description
 */

@RestController
@Api(tags = "用户首页")
@RequestMapping("/user/index")
public class UserIndexController {

    @Resource
    private UserIndexService indexService;

    @ApiOperation("轮播图")
    @GetMapping("/banner")
    public R<BannerVo> getBannerList(@ApiParam("所需轮播图数量，默认4")
                                     @RequestParam(value = "count", defaultValue = "4") Integer count) {

        return R.ok("请求成功", indexService.getBannerList(count));
    }

}
