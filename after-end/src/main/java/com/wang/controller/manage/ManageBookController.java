package com.wang.controller.manage;

import com.wang.pojo.bo.BookBo;
import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.OrderSearchBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BookVo;
import com.wang.pojo.vo.ManOrderVo;
import com.wang.pojo.vo.PageData;
import com.wang.pojo.vo.TypeVo;
import com.wang.service.manage.ManageBookService;
import com.wang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/add")
    public R<Void> addBook(@RequestBody BookBo bo) {
        return bookService.addBook(bo) ? R.ok("添加成功") : R.fail("添加失败");
    }

    @ApiOperation("修改书籍")
    @PostMapping("/update")
    public R<Void> updateBook(@RequestBody BookBo bo) {
        return bookService.updateBook(bo) ? R.ok("修改成功") : R.fail("修改失败");
    }

    @ApiOperation("上架/下架书籍")
    @PostMapping("/shelve")
    public R<Void> shelveBook(@RequestParam(value = "bookId", required = true)
                              @ApiParam("书籍id") Long bookId) {
        return bookService.shelveBook(bookId) ? R.ok("修改成功") : R.fail("修改失败");
    }

    @ApiOperation("订单列表")
    @GetMapping("/orders")
    public R<PageData<ManOrderVo>> getOrderList(OrderSearchBo searchBo, PageQuery pageQuery) {
        return R.ok("请求成功", bookService.getOrderList(searchBo, pageQuery));
    }

    @ApiOperation("订单详细信息")
    @GetMapping("/order/{orderId}")
    public R<ManOrderVo> getOrderInfo(@PathVariable("orderId") @ApiParam("订单id")
                                      Long orderId) {
        return R.ok("请求成功", bookService.getOrderInfo(orderId));
    }

    @ApiOperation("书籍类型列表")
    @GetMapping("/types")
    public R<PageData<TypeVo>> getTypeList(PageQuery pageQuery) {
        return R.ok("请求成功", bookService.getTypeList(pageQuery));
    }

    @ApiOperation("书籍类型列表")
    @GetMapping("/types/noPage")
    public R<List<TypeVo>> getTypeList() {
        return R.ok("请求成功", bookService.getTypeList());
    }

    @ApiOperation("删除书籍类型")
    @PostMapping("/delType")
    public R<Void> delType(@RequestParam("typeId") @ApiParam("书籍类型Id") Long typeId) {
        return bookService.delType(typeId) ? R.ok("删除成功") : R.fail("删除失败");
    }

    @ApiOperation("添加书籍类型")
    @PostMapping("/addType")
    public R<Void> addType(@RequestBody @ApiParam("书籍类型名称") Map<String, String> map) {
        return bookService.addType(map.get("typeName")) ? R.ok("添加成功") : R.fail("添加失败");
    }

}
