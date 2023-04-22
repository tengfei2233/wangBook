package com.wang.controller.manage;

import com.wang.pojo.bo.BannerBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BannerVo;
import com.wang.pojo.vo.PageData;
import com.wang.service.manage.ManageBannerService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: tengfei
 * @Create: 2023 04 22 10:48
 * @Description
 **/

@Api(tags = {"轮播图管理控制器"})
@RestController
@RequestMapping("/manage/banner")
public class ManageBannerController {

    @Resource
    private ManageBannerService bannerService;

    @ApiOperation("轮播图列表")
    @GetMapping("/banners")
    public R<PageData<BannerVo>> getBannerList(PageQuery pageQuery) {
        return R.ok("请求成功", bannerService.getBannerList(pageQuery));
    }

    @ApiOperation("添加轮播图")
    @PostMapping("/add")
    public R<Void> addBanner(@RequestBody BannerBo bo) {
        return bannerService.addBanner(bo)?R.ok("添加成功"):R.fail("添加失败");
    }

    @ApiOperation("修改轮播图")
    @PostMapping("/update")
    public R<Void> updateBanner(@RequestBody BannerBo bo) {
        return bannerService.updateBanner(bo)?R.ok("修改成功"):R.fail("修改失败");
    }

    @ApiOperation("删除轮播图")
    @PostMapping("/delete")
    public R<Void> deleteBanner(@RequestParam("bnId")@ApiParam("轮播图id")Long bnId) {
        return bannerService.deleteBanner(bnId)?R.ok("删除成功"):R.fail("删除失败");
    }

}
