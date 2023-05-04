package com.wang.controller.manage;

import com.wang.pojo.bo.CommentSearchBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.CommentVo;
import com.wang.pojo.vo.PageData;
import com.wang.service.manage.ManageCommentService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: tengfei
 * @Create: 2023 05 04 17:03
 * @Description
 **/


@Api(tags = {"评论管理控制器"})
@RestController
@RequestMapping("/manage/comment")
public class ManageCommentController {

    @Resource
    private ManageCommentService commentService;

    @ApiOperation("评论列表")
    @GetMapping("/comments")
    public R<PageData<CommentVo>> getCommentList(CommentSearchBo searchBo, PageQuery pageQuery) {
        return R.ok("请求成功", commentService.getCommentList(searchBo, pageQuery));
    }

    @ApiOperation("删除评论")
    @PostMapping("/del")
    public R<Void> delComment(@RequestParam("cmId") @ApiParam("评论id") Long cmId) {
        return commentService.delComment(cmId) ? R.ok("删除成功") : R.fail("删除失败");
    }

}
