package com.wang.controller.user;

import com.wang.service.user.UserCommonService;
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
 * @Create: 2023 04 28 22:36
 * @Description
 **/

@Api(tags = {"通用接口控制器"})
@RestController
@RequestMapping("/user/common")
public class UserCommonController {

    @Resource
    private UserCommonService commonService;

    @ApiOperation("图片上传")
    @PostMapping("/uploadImg")
    public R<String> uploadImg(@RequestParam("img") MultipartFile file) {
        return R.ok("上传成功", commonService.uploadImg(file));
    }

}
