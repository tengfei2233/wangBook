package com.wang.controller.manage;

import com.wang.service.manage.ManageDashboardService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: tengfei
 * @Create: 2023 04 26 10:48
 * @Description
 **/

@Api(tags = {"轮播图管理控制器"})
@RestController
@RequestMapping("/manage/dashboard")
public class MangeDashboardController {

    @Resource
    private ManageDashboardService dashboardService;

    @ApiOperation("轮播图列表")
    @GetMapping("/chartData")
    public R<Map<String, Object>> getChartData() {
        return R.ok("请求成功", dashboardService.getChartData());
    }

}
