package com.wang.controller.manage;

import com.wang.service.manage.ManageCommonService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: tengfei
 * @Create: 2023 04 24 16:39
 * @Description
 **/

@Api(tags = {"通用接口控制器"})
@RestController
@RequestMapping("/manage/common")
public class ManageCommonController {

    @Resource
    private ManageCommonService commonService;

    @ApiOperation("图片上传")
    @PostMapping("/uploadImg")
    public R<String> uploadImg(@RequestParam("img") MultipartFile file) {
        return R.ok("上传成功", commonService.uploadImg(file));
    }

}
