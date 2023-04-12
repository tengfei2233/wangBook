package com.wang.service.user;

import com.wang.pojo.bo.BookSearchBo;
import com.wang.pojo.bo.CommentBo;
import com.wang.pojo.bo.PageQuery;
import com.wang.pojo.vo.BookVo;
import com.wang.pojo.vo.CommentVo;
import com.wang.pojo.vo.PageData;
import com.wang.pojo.vo.TypeVo;

/**
 * @author feige
 * @version v1.0
 * @date 2023-04-01-20:51
 * @description
 */
public interface UserBookService {
    TypeVo getTypeList();

    PageData<BookVo> getBookList(BookSearchBo searchBo, PageQuery pageQuery);

    BookVo getBookInfo(Long bookId);

    PageData<CommentVo> CommentList(Long bookId, PageQuery pageQuery);

    Boolean setComment(CommentBo bo);
}
