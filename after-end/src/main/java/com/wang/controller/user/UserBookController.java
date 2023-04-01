package com.wang.controller.user;

import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BookVo;
import com.wang.pojo.vo.CommentVo;
import com.wang.pojo.vo.PageData;
import com.wang.pojo.vo.TypeVo;
import com.wang.service.user.UserBookService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-31-23:02
 * @description
 */

@RestController
@Api(tags = "书籍列表")
@RequestMapping("/user/book")
public class UserBookController {

    @Resource
    private UserBookService bookService;

    @ApiOperation("书籍分类列表")
    @GetMapping("/types")
    public R<TypeVo> getTypeList() {
        return R.ok("请求成功", bookService.getTypeList());
    }

    @ApiOperation("书籍列表")
    @GetMapping("/books")
    public R<PageData<BookVo>> getBookList(BookSearchBo searchBo, PageQuery pageQuery) {
        return R.ok("请求成功", bookService.getBookList(searchBo, pageQuery));
    }

    @ApiOperation("书籍详细信息")
    @GetMapping("/bookInfo/{bookId}")
    public R<BookVo> getBookInfo(@ApiParam("书籍id") @PathVariable("bookId") Long bookId) {
        return R.ok("请求成功", bookService.getBookInfo(bookId));
    }

    @ApiOperation("评论列表")
    @GetMapping("/comments")
    public R<PageData<CommentVo>> getCommentList(Long bookId, PageQuery pageQuery) {
        return R.ok("请求成功", bookService.CommentList(bookId, pageQuery));
    }


}
