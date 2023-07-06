package com.wang.controller.user;

import com.alipay.api.AlipayApiException;
import com.wang.pojo.bo.AddOrderBo;
import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.CommentBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.*;
import com.wang.service.user.UserBookService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    public R<List<TypeVo>> getTypeList() {
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

    @ApiOperation("加入购物车")
    @PostMapping("/addCar")
    public R<Void> addCar(@RequestBody AddOrderBo bo) {
        return bookService.addCar(bo) ? R.ok("添加成功") : R.fail("添加失败");
    }

    @ApiOperation("购买书籍")
    @GetMapping( "/buyBook")
    public String buyBook(AddOrderBo bo) {
        return bookService.buyBook(bo);
    }


    @ApiOperation("付款成功同步回调")
    @GetMapping("/syncNotify")
    public void returnUrl( HttpServletResponse response) throws AlipayApiException, IOException {
        bookService.returnUrl( response);
    }

    @ApiOperation("付款成功异步回调")
    @PostMapping("/asyncNotify")
    public String notifyUrl(HttpServletRequest request) throws AlipayApiException, IOException {
        return bookService.notifyUrl(request);
    }

    @ApiOperation("购物车列表")
    @GetMapping("/cars")
    public R<PageData<CarVo>> getCarList(PageQuery pageQuery) {
        return R.ok("请求成功", bookService.carList(pageQuery));
    }

    @ApiOperation("删除购物车")
    @PostMapping("/delCar")
    public R<Void> delCar(@RequestParam @ApiParam("id") Long id) {
        return bookService.delCar(id) ? R.ok("删除成功") : R.ok("删除失败");
    }

    @ApiOperation("订单列表")
    @GetMapping("/orders")
    public R<PageData<OrderVo>> getOrderList(PageQuery pageQuery) {
        return R.ok("请求成功", bookService.orderList(pageQuery));
    }

    @ApiOperation("删除订单")
    @PostMapping("/delOrder")
    public R<Void> delOrder(@RequestParam @ApiParam("orderId") Long orderId) {
        return bookService.delOrder(orderId) ? R.ok("删除成功") : R.ok("删除失败");
    }

    @ApiOperation("评论列表")
    @GetMapping("/comments")
    public R<PageData<CommentVo>> getCommentList(Long bookId, PageQuery pageQuery) {
        return R.ok("请求成功", bookService.CommentList(bookId, pageQuery));
    }


    @ApiOperation("发表评论")
    @PostMapping("/setComment")
    public R<Void> setComment(@RequestBody CommentBo bo) {
        return bookService.setComment(bo) ? R.ok("发表成功") : R.fail("发表失败");
    }


}
