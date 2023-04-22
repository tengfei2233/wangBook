package com.wang.service.manage;

import com.wang.pojo.bo.BookBo;
import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.OrderSearchBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BookVo;
import com.wang.pojo.vo.ManOrderVo;
import com.wang.pojo.vo.PageData;

/**
 * @Author: tengfei
 * @Create: 2023 04 21 23:56
 * @Description
 **/

public interface ManageBookService {
    PageData<BookVo> getBookList(BookSearchBo searchBo, PageQuery pageQuery);

    Boolean addBook(BookBo bo);

    Boolean updateBook(BookBo bo);

    Boolean shelveBook(Long bookId);

    PageData<ManOrderVo> getOrderList(OrderSearchBo searchBo, PageQuery pageQuery);

    ManOrderVo getOrderInfo(Long orderId);
}
