package com.wang.controller.manage;

import com.wang.pojo.bo.BookBo;
import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BookVo;
import com.wang.pojo.vo.PageData;
import com.wang.service.manage.ManageBookService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 23:54
 * @Description
 **/

@Api(tags = {"书籍管理控制器"})
@RestController
@RequestMapping("/manage/book")
public class ManageBookController {

    @Resource
    private ManageBookService bookService;

    @ApiOperation("书籍列表")
    @GetMapping("/books")
    public R<PageData<BookVo>> getBookList(BookSearchBo searchBo, PageQuery pageQuery) {
        return R.ok("请求成功", bookService.getBookList(searchBo, pageQuery));
    }


    @ApiOperation("添加书籍")
    @GetMapping("/add")
    public R<Void> addBook(BookBo bo) {
        return bookService.addBook(bo)?R.ok("添加成功"):R.fail("添加失败");
    }

    @ApiOperation("修改书籍")
    @GetMapping("/update")
    public R<Void> updateBook(BookBo bo) {
        return bookService.updateBook(bo)?R.ok("修改成功"):R.fail("修改失败");
    }


}
