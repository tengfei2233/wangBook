package com.wang.service.user;

import com.alipay.api.AlipayApiException;
import com.wang.pojo.bo.AddOrderBo;
import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.CommentBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-20:51
 * @description
 */
public interface UserBookService {
    List<TypeVo> getTypeList();

    PageData<BookVo> getBookList(BookSearchBo searchBo, PageQuery pageQuery);

    BookVo getBookInfo(Long bookId);

    PageData<CommentVo> CommentList(Long bookId, PageQuery pageQuery);

    Boolean setComment(CommentBo bo);

    Boolean addCar(AddOrderBo bo);

    String buyBook(AddOrderBo bo,String token);

    void returnUrl(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException;

    PageData<OrderVo> orderList(PageQuery pageQuery);

    PageData<CarVo> carList(PageQuery pageQuery);

    Boolean delCar(Long id);

    Boolean delOrder(Long orderId);
}
